package com.tfjybj.english.provider.service.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.github.tobato.fastdfs.service.TrackerClient;
import com.google.common.collect.Lists;
import com.tfjybj.english.entity.UserSetEntity;
import com.tfjybj.english.entity.WordEntity;
import com.tfjybj.english.model.*;
import com.tfjybj.english.provider.dao.UserSetDao;
import com.tfjybj.english.provider.dao.WordDao;
import com.tfjybj.english.provider.service.NewPictureService;
import com.tfjybj.english.provider.service.WordService;
import com.dmsdbj.itoo.tool.base.service.impl.BaseServicePlusImpl;
import com.tfjybj.english.provider.service.common.RankService;
import com.tfjybj.english.provider.service.common.RedisToDbService;
import com.tfjybj.english.provider.service.common.WordListService;
import com.tfjybj.english.provider.until.image.SimilarImageSearch;
import com.tfjybj.english.utils.EnglishRedis;
import com.tfjybj.english.utils.RecordDate;
import com.tfjybj.english.utils.cache.RedisUtil;
import com.tfjybj.english.utils.cache.UploadPictureUntil;
import groovy.util.logging.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.*;

/**
 * WordService接口实现类
 * ${base}表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since 2019-08-16 08:47:57
 */
@Slf4j
@Service("wordService")
public class WordServiceImpl extends BaseServicePlusImpl<WordDao, WordEntity> implements WordService {

    @Autowired
    NewPictureService newPictureService;

    @Autowired
    WordListService wordListService;

    @Autowired
    UploadPictureUntil uploadPictureUntil;
    //region 模板生成
    @Resource
    private WordDao wordDao;
    
  


    //获取当天的时间
    Calendar cal = Calendar.getInstance();
    String day = String.valueOf(cal.get(Calendar.MONTH) + 1) + cal.get(Calendar.DAY_OF_MONTH);

    //获取地址
    String imagePath;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    RedisToDbService redisToDbService;

    @Autowired
    UserSetDao userSetDao;

    @Autowired
    RankService rankService;

    @Value("${fdfs.groupName}")
    private String GROUP_NAME;

    @Resource
    private FastFileStorageClient fastFileStorageClient;
    @Resource
    private TrackerClient trackerClient;

    public static String FILE_FORMAT = "WEBP、BMP、PCX、TIF、GIF、JPEG、TGA、EXIF、FPX、SVG、PSD、CDR、PCD、DXF、UFO、EPS、AI、PNG、HDRI、RAW、WMF、FLIC、EMF、ICO、JPG、JPEG、PNG、GIF";


    /* **********************************以下为非模板生成的内容********************************* */

    public long queryWordNumsBar() {
        // 首先要判断今天待学习的学习数量<0?
        //  小于0 直接返回
        // 大于0 返回redis中待学习的数量
        int needStudyNums = wordDao.findWordnumsById(UserUtil.getCurrentUser().getUserId()); //今天还需要的学习数量 = 今天设置的单词数 - 今天已学单词
        if (needStudyNums < 0) {
            return  needStudyNums;
        } else {
            long redisNums = redisUtil.lGetListSize(EnglishRedis.Record + UserUtil.getCurrentUser().getUserId() + day + EnglishRedis.WordToDo);
            return redisNums;
        }
    }

    /**
     * 进入学单词页面
     * ${base}表
     *
     * @author 邢美玲
     * @version 1.0.0
     * @since 2019-08-16 08:47:57
     */
    @Override
    public WordPartModel findWordsById() {
        System.out.println(UserUtil.getCurrentUser().getUserId());
        //从redis中Done数据同步到数据库中
        Boolean flag = redisToDbService.doneToDB(UserUtil.getCurrentUser().getUserId());
        if (!flag) {
            return null;
        }
        // 判断 Redis中的todo是否有数据
        // 如果有数据，就需要return next
//        if (redisUtil.hasKey(EnglishRedis.Record + UserUtil.getCurrentUser().getUserId() + day + EnglishRedis.WordToDo)) {
//            return getNextWord();
//        } else {
            //获取今天要学习的单词数量：
            //获取该用户的setting数量和今天已学单词的数量，相减(count)
            //count:如果数值小于等于0，为setting值；如果大于0，为该数量
            Boolean isExitUser = redisUtil.hasKey(EnglishRedis.Set+ UserUtil.getCurrentUser().getUserId());
            UserSetEntity userSetEntity = new UserSetEntity();
            if (!isExitUser) {
                // 缓存中没有,查询数据库
                userSetEntity = wordDao.findUserSetById(UserUtil.getCurrentUser().getUserId());
            } else {
                //redis中有查询redis返回userSetEntity
                String userSetInfo = redisUtil.get(EnglishRedis.Set + UserUtil.getCurrentUser().getUserId());
                userSetEntity = JSON.parseObject(userSetInfo, UserSetEntity.class);
                // 判断redis中是否可以获取到studynums,没有值那么走数据库
                if(userSetEntity.getStudyNumber() == null){
                    // 缓存中没有,查询数据库
                    userSetEntity = wordDao.findUserSetById(UserUtil.getCurrentUser().getUserId());
                }
            }
            int needStudyNums = wordDao.findWordnumsById(UserUtil.getCurrentUser().getUserId()); //今天还需要的学习数量 = 今天设置的单词数 - 今天已学单词
            // 判断所有单词是否全部学完
            int unStudyNums = wordDao.findOtherworsById(UserUtil.getCurrentUser().getUserId());// 未学习的单词数量 = 所有单词 - 该用户已学单词
            List<WordPartModel> listWord = new ArrayList<>();// 当天待学习的单词ID
            List<WordPartModel> listOldWord = new ArrayList<>();// 第一轮完成或许学过的待学习单词ID
            if (needStudyNums > 0) {
            } else {
                //把设置的数量赋值给今天需要学习的needStudyNums
                needStudyNums = userSetEntity.getStudyNumber();
            }
            //  没有全部学完
            if (unStudyNums >= needStudyNums) {
                listWord = wordDao.findWordIdByWordnums(UserUtil.getCurrentUser().getUserId(), needStudyNums);
             // 已经全部学完
            } else {
                int tostudywords = needStudyNums - unStudyNums;// 取出历史学习过的单词数量
                listWord = wordDao.findWordIdByWordnums(UserUtil.getCurrentUser().getUserId(), unStudyNums);
                listOldWord = wordDao.findWordIDByToStudyWords(UserUtil.getCurrentUser().getUserId(), tostudywords);
                listWord.addAll(listOldWord);
            }
            // 把所有待学习的单词和图片放入到redis中
            redisUtil.del(EnglishRedis.Record + UserUtil.getCurrentUser().getUserId()+day+EnglishRedis.WordToDo);
            redisUtil.lSetAll(EnglishRedis.Record + UserUtil.getCurrentUser().getUserId() + day + EnglishRedis.WordToDo, listWord, 24 * 3600);
            return getNextWord();
//        }
    }

    /**
     * 学单词-点击下一步
     * xml
     * @param
     * @return
     */
    public WordPartModel getNextWord() {
        System.out.println(UserUtil.getCurrentUser().getUserId());

        // 判断缓存中是否有待学习的内容
        if (!redisUtil.hasKey(EnglishRedis.Record + UserUtil.getCurrentUser().getUserId() + day + EnglishRedis.WordToDo)) {
            if (redisUtil.hasKey(EnglishRedis.Record + UserUtil.getCurrentUser().getUserId() + RecordDate.Date() + EnglishRedis.Done)) {
                // 将redis的Done的单词同步到数据库中
                redisToDbService.doneToDB(UserUtil.getCurrentUser().getUserId());
                return null;
            }
        }
        // 从redis中按照顺序取出需要学习的单词
        WordPartModel wordPartModel = JSON.parseObject((String) redisUtil.leftPop(EnglishRedis.Record + UserUtil.getCurrentUser().getUserId() + day + EnglishRedis.WordToDo), WordPartModel.class);

        // E 币数量加1-
        rankService.addE(UserUtil.getCurrentUser().getUserId(), 1);

        //根据当前用户的wordId把学习完的插入到doneWords
        String wordId = wordPartModel.getId();
        redisUtil.sSet(EnglishRedis.Record + UserUtil.getCurrentUser().getUserId() + RecordDate.Date() + EnglishRedis.Done, wordId);
        // 判断是否有新图，将新图表中的数据替换旧的
        if (!redisUtil.hasKey(EnglishRedis.NewPicture + UserUtil.getCurrentUser().getUserId() + "_" + wordId)) {
            return wordPartModel;
        }
        UserNewpictureModel userNewPicture = JSON.parseObject(redisUtil.get(EnglishRedis.NewPicture + UserUtil.getCurrentUser().getUserId() + "_" + wordId), UserNewpictureModel.class);
        if (userNewPicture == null) {
            return wordPartModel;
        }
        String[] pictureStr = userNewPicture.getPictureAddress().split(",");
        List<String> pictures = new ArrayList<>();
        for (int i = 0; i < pictureStr.length; i++) {
            pictures.add(pictureStr[i]);
        }
        pictures.add(wordPartModel.getWordPicture1());
        pictures.add(wordPartModel.getWordPicture2());
        pictures.add(wordPartModel.getWordPicture3());
        pictures.add(wordPartModel.getWordPicture4());

        wordPartModel.setWordPicture1(pictures.get(0));
        wordPartModel.setWordPicture2(pictures.get(1));
        wordPartModel.setWordPicture3(pictures.get(2));
        wordPartModel.setWordPicture4(pictures.get(3));
        return wordPartModel;
    }

    /**
     * 点击字母更新学习列表
     * xml
     *
     * @param
     * @return
     */
    @Override
    public void undateWordList(String word) {
        System.out.println(UserUtil.getCurrentUser().getUserId());
        List<WordModel> wordModels = wordListService.findWordByNameR(word);
        if (wordModels == null) {
            return;
        }
        if (!redisUtil.hasKey(EnglishRedis.Record + UserUtil.getCurrentUser().getUserId() + day + EnglishRedis.WordToDo)) {
            return;
        }
        // redisNums今天需要学习的数量
        long redisNums = redisUtil.lGetListSize(EnglishRedis.Record + UserUtil.getCurrentUser().getUserId() + day + EnglishRedis.WordToDo);
        //  wordNums字母开头的所有单词数量
        long wordNums = wordModels.size();
        // 如果今日需要学习的数多(先学字母开头+今日todo学习)
        if (redisNums > wordNums) {
            long restNums = redisNums - wordNums;
            redisUtil.lTrim(EnglishRedis.Record + UserUtil.getCurrentUser().getUserId() + day + EnglishRedis.WordToDo, 1, restNums);
//            List<WordPartModel> wordPartModels = Lists.newArrayList();
//            BeanUtils.copyProperties(wordModels, wordPartModels);
            redisUtil.lLeftSetAll(EnglishRedis.Record + UserUtil.getCurrentUser().getUserId() + day + EnglishRedis.WordToDo, wordModels);
            return;
        }
        // 字母开头的单词多（都变成字母开头单词）
        wordModels = wordModels.subList(0, Integer.parseInt(String.valueOf(redisNums)) - 1);
//        List<WordPartModel> wordPartModels = Lists.newArrayList();
//        BeanUtils.copyProperties(wordModels, wordPartModels);
        redisUtil.del(EnglishRedis.Record + UserUtil.getCurrentUser().getUserId() + day + EnglishRedis.WordToDo);
        redisUtil.lSetAll(EnglishRedis.Record + UserUtil.getCurrentUser().getUserId() + day + EnglishRedis.WordToDo, wordModels, 24 * 3600);
    }

    /**
     * 添加图到数据库中
     *      * xml
     *
     * @return
     */
    @Override
    public boolean insertPicture(NewPictureAddress newPictureAddress) {
        //从所有图片的redis中查询，对应图片word的pictureaddress放入到list中
        System.out.println(UserUtil.getCurrentUser().getUserId());
        String userId = UserUtil.getCurrentUser().getUserId();
        // String  userId = "1";
        newPictureAddress.setUserId(userId);
        boolean flag = true;
        // 判断新图缓存中是否有这个用户的新图ID
        // 如果有,取出来做对比
        // 如果没有，直接插入到数据库中，带着三个参数 userId ,word, wordId
        if (redisUtil.hasKey(EnglishRedis.NewPicture + userId + "_" + newPictureAddress.getWordId())) {
            // 取出这个单词下的图片 string类型转换成model类型
            UserNewpictureModel  address = JSON.parseObject(redisUtil.get(EnglishRedis.NewPicture + userId + "_" + newPictureAddress.getWordId()), UserNewpictureModel.class);
            String addresses = address.getPictureAddress();
           String[] addressList = addresses.split(",");
            List<String> addressArray = Arrays.asList(addressList);
            // 以上是对获取的图片转格式
            // 判断图片地址是否是空的
            if (!CollectionUtils.isEmpty(addressArray)) {
                // 不是空的，循环遍历做对比
                for (int i = 0; i < addressArray.size(); i++) {
                    String hashCode = SimilarImageSearch.produceFingerPrint(newPictureAddress.getPictureAddress());
                    String SourceCode = SimilarImageSearch.produceFingerPrint(addressArray.get(i));
                    int difficulty = SimilarImageSearch.hammingDistance(hashCode, SourceCode);
                    // 如果不相似度小于10，那么不插入，直接返回
                    if (difficulty < 5) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    // 带着参数，把单词插入到数据库中
                    wordDao.insertNewPicturea(IdWorker.getIdStr(), newPictureAddress.getUserId(), newPictureAddress.getWordId(), newPictureAddress.getPictureAddress());
                    // 把新添加的图片set到缓存中
                    addresses = addresses + "," + newPictureAddress.getPictureAddress();
                    UserNewpictureModel userNewpictureModel = new UserNewpictureModel();
                    userNewpictureModel.setWordId(newPictureAddress.getWordId());
                    userNewpictureModel.setWord(newPictureAddress.getWord());
                    userNewpictureModel.setPictureAddress(addresses);
                    redisUtil.set(EnglishRedis.NewPicture + userId + "_" + userNewpictureModel.getWordId(), JSON.toJSONString(userNewpictureModel));
                }
            }
        } else {
            // 带着参数，把单词插入到数据库中
            wordDao.insertNewPicturea(IdWorker.getIdStr(), newPictureAddress.getUserId(), newPictureAddress.getWordId(), newPictureAddress.getPictureAddress());
            // 把新添加的图片set到缓存中
            redisUtil.set(EnglishRedis.NewPicture + userId + "_" + newPictureAddress.getWordId(), JSON.toJSONString(newPictureAddress));
        }
        return flag;
    }

    // 插入fastdfs---邢美玲--2019年8月21日
    @Override
    public String upLoadPicture(MultipartFile file) throws IOException {
        if (FILE_FORMAT.contains(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.') + 1).toUpperCase())){
//        StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
            StorePath storePath =  fastFileStorageClient.uploadFile(GROUP_NAME,file.getInputStream(), file.getSize(),FilenameUtils.getExtension(file.getOriginalFilename()));
            String serverPath = trackerClient.getStoreStorage().getIp();
            imagePath = "http://" + serverPath + "/" + storePath.getFullPath();
            //插入图片数据库
           // newPictureAddress.setPictureAddress(imagePath);
            //insertPicture(newPictureAddress);
            //  返回插入的图片途径
            return imagePath;}else{
                return null;
            }
    }

    /**
     * 将文件导入到数据库
     * 陈广晗
     * @param path (文件包)
     * @return 成功还是失败
     * @throws IOException
     */
    @Override
    public boolean batchInsert(String path) throws IOException {
        List<WordEntity> wordEntitieList = new ArrayList<>();
        boolean flag = false;
        File file = new File(path);

        // 获取当前路径下面所有文件
        File[] wordFilesList = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File wordPathName) {
                return wordPathName.isFile();
            }
        });
        // 获取当前文件下面所有文件夹
        File[] wordFilesDic = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File wordPathName) {
                return wordPathName.isDirectory();
            }
        });
        // 循环存放只有文件的集合
        if (wordFilesDic.length == 0 && wordFilesList.length > 0) {
            WordEntity wordEntity = new WordEntity();
            // 获取文件夹名称
            wordEntity.setWord(path.substring(path.lastIndexOf('\\') + 1));
            int picNum = 0;
            // 循环当前文件下面所有的文件
            for (int j = 0; j < wordFilesList.length; j++) {
                // 上传文件,返回文件上传完整路径
                String picture = uploadPictureUntil.uploadPicture(wordFilesList[j]);
                // 通过截取路径获取后缀
                if (picture == "" || picture == null) {
//                    log.error("文件上传失败!");
                    continue;
                }
                // 判断上传的文件是否符合图片格式
                if (UploadPictureUntil.FILE_FORMAT.contains(picture.substring(picture.lastIndexOf('.') + 1).toUpperCase())) {
                    picNum++;
                    switch (picNum) {
                        case 1:
                            wordEntity.setWordPicture1(picture);
                            continue;
                        case 2:
                            wordEntity.setWordPicture2(picture);
                            continue;
                        case 3:
                            wordEntity.setWordPicture3(picture);
                            continue;
                        case 4:
                            wordEntity.setWordPicture4(picture);
                            continue;
                        case 5:
                            wordEntity.setWordPicture5(picture);
                            continue;
                    }
                    // 判断是否符合音频格式
                } else if (UploadPictureUntil.AUDIO_FREQUENCY_FORMAT.contains(picture.substring(picture.lastIndexOf('.') + 1).toUpperCase())) {
                    wordEntity.setAudio(picture);
                }
            }
            // 保存到数据库中
            flag = this.save(wordEntity);

            // 递归
        } else if (wordFilesDic.length > 0 && wordFilesList.length == 0) {
            File[] tempList = file.listFiles();
            for (int i = 0; i < tempList.length; i++) {
                batchInsert(tempList[i].getAbsolutePath());
            }
        }
        return flag;
    }
}

