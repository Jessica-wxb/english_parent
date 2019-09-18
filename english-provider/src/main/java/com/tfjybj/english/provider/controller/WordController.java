package com.tfjybj.english.provider.controller;

import com.alibaba.fastjson.JSONObject;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.tfjybj.english.model.*;
import com.tfjybj.english.provider.service.WordService;
import com.tfjybj.english.model.UserNewpictureModel;
import com.tfjybj.english.model.WordModel;
import com.dmsdbj.itoo.tool.business.ItooResult;
import com.tfjybj.english.provider.service.common.WordListService;
import com.tfjybj.english.utils.cache.RedisUtil;
import groovy.util.logging.Slf4j;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;


/**
 * WordController
 * word表
 *
 * @author 张凯超
 * @version 1.0.0
 * @since  2019-08-16 08:47:57
 */
@Api(tags = {"word表接口"})
@Slf4j
@RequestMapping(value = "/word")
@RestController
public class WordController {

    @Autowired
    private WordListService wordListService;

    @Autowired
    private WordService wordOtherService;
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    WordService wordService;

    private static String ENGLISH_WORDTODO = "English:WordTODO";

    //获取当天的时间
    Calendar cal = Calendar.getInstance();
    String day = String.valueOf(cal.get(Calendar.MONTH) + 1) + cal.get(Calendar.DAY_OF_MONTH);

    /**
     * 根据wordId查找属于它的图片
     *
     * @param wordId 单词Id
     * @author 闫伟强
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "根据wordId查找属于它的图片")
    @GetMapping(value = {"/findById"})
    public ItooResult findWordPicture(String wordId,String word) {
        WordModel userNewpictureModel = wordListService.findWordPicture(wordId,word);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", userNewpictureModel);
    }


    /**
     * 通过单词拼写模糊查询单词 包含就可以
     *
     * @param word 单词拼写
     * @author 闫伟强
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "通过单词拼写模糊查询单词 包含就可以")
    @GetMapping(value = {"/findByIdLR"})
    public ItooResult findWordLR(String word) {
        List<WordModel> wordModels = wordListService.findWordByNameLR(word);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", wordModels);
    }

    /**
     * 通过单词拼写模糊查询单词 以A-Z打头
     *
     * @param word 单词拼写
     * @author 闫伟强
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "通过单词拼写模糊查询单词 以A-Z打头")
    @GetMapping(value = {"/findByIdR"})
    public ItooResult findWordR(String word) {
        List<WordModel> wordModels = wordListService.findWordByNameR(word);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", wordModels);
    }

    /**
     * 将word表插入Redis
     *
     * @author 闫伟强
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "将word表插入Redis")
    @GetMapping(value = {"/wordInsertRedis"})
    public ItooResult wordInsertRedis() {
        List<WordModel> wordModels = wordListService.wordInsertRedis();
        return ItooResult.build(ItooResult.SUCCESS, "插入成功", wordModels);
    }

    //endregion

    /* **********************************以下为非模板生成的内容********************************* */
    /**
     * 进入学单词页面初始化加载 根据用户ID，查询需学习的单词ID，audio，picture
     * @param
     * @return 根据id查找的结果
     * @author 邢美玲
     * @version 1.0.0
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "进入学单词页面初始化加载")
    @GetMapping(value = {"/queryWordNewPicture"})
    public ItooResult findWordsById(String userCode) {
        WordPartModel listwords = wordOtherService.findWordsById(userCode);//董可 增加了userCode

        return ItooResult.build(ItooResult.SUCCESS, "查询成功", listwords);
    }
    /**
     * 学单词点击下一步，查询需要学习的单词的ID，audio,picture
     * @param
     * @return 根据id查找的结果
     * @author 邢美玲
     * @version 1.0.0
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "点击下一步，顺序查询需要学习的内容")
    @GetMapping(value = {"/getNextWord"})
    public ItooResult getNextWord(String userCode) {
        WordPartModel nextwords = wordOtherService.getNextWord(userCode);//董可添加userCode
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", nextwords);
    }
    /**
     * 点击字母查询字母开头的单词学习列表
     * @param
     * @return 根据id查找的结果
     * @author 邢美玲
     * @version 1.0.0
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "点击字母查询字母开头的单词学习列表")
    @GetMapping(value = {"/queryLetterAll"})
    public ItooResult getWordList(@ApiParam(value = "开头字母拼写", required = true) String word) {
//        System.out.println(UserUtil.getCurrentUser().getUserId());
        wordOtherService.undateWordList(word);
       return ItooResult.build(ItooResult.SUCCESS, "查询成功");
    }
    /**
     * 根据学生id和图片插入到新图表
     * @return
     * @author 邢美玲
     * @version 2.0.0
     * @since 2019年8月17日21:02:00
     */
    @ApiOperation(value = "长按添加图片")
    @PostMapping(value = {"/insertNewPicture"})
    public ItooResult createNewPicture(@RequestBody NewPictureAddress newPictureAddress) {
        //        System.out.println(UserUtil.getCurrentUser().getUserId());
        //        String userId = UserUtil.getCurrentUser().getUserId();
        if(newPictureAddress.getWordId() == null || newPictureAddress.getPictureAddress() == null){
            return ItooResult.build(ItooResult.FAIL, "报错了！");
        }
        boolean flag = wordOtherService.insertPicture(newPictureAddress);
        if(flag){
            return ItooResult.build(ItooResult.SUCCESS, "添加成功");
        }
        else {
            return ItooResult.build(ItooResult.FAIL,"添加失败");
        }
    }

    /**
     * 添加新图到图片数据库
     * @return
     * @author 邢美玲
     * @version 1.0.0
     * @since 2019年8月21日19:47:53
     */
    @ApiOperation(value = "上传图片到fastdfs")
    @PostMapping(value = "/upLoad")
    public ItooResult upLoad(@RequestBody MultipartFile multfile) {
//      System.out.println(UserUtil.getCurrentUser().getUserId());
//        String userId = "123";
        try {
//            NewPictureAddress newPictureAddress = new NewPictureAddress();
//            newPictureAddress.setWordId(word);
//            newPictureAddress.setWord(wordId);
//            if (StringUtils.isEmpty(word) || StringUtils.isEmpty(wordId)) {
//                return ItooResult.build("1111", "参数错误！");
//            }
            String s = wordOtherService.upLoadPicture(multfile);
            return ItooResult.build("0000","上传成功",s);
        } catch (Exception e) {
            return ItooResult.build("1111", "上传失败");
        }
    }

    /**
     * 进度条，查询redis中该用户还待学习的数量：
     * @param
     * @return 根据id和日期查找
     * @author 邢美玲
     * @version 1.0.0
     * @since 2019年8月25日21:49:13
     */
    @ApiOperation(value = "查询redis中该用户还待学习的数量")
    @GetMapping(value = {"/queryWordNums"})
    public ItooResult queryWordNums(){
        System.out.println(UserUtil.getCurrentUser().getUserId());
        long redisNums  = wordOtherService.queryWordNumsBar();
        return ItooResult.build(ItooResult.SUCCESS, "查询成功",redisNums);
    }

    /**
     * 分页查询所有Word
     *
     * @return 分页查询的结果
     * @author 闫伟强
     * @version 1.0.0
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "分页查询所有Word")
    @GetMapping(value = {"/queryPageAll"})
    public ItooResult queryPageAll( Integer pageNo,Integer pageSize) {
        List<WordModel> wordModel = wordListService.queryPageAll(pageNo,pageSize);
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", wordModel);
    }

    /**
     * 查询所有Word
     *
     * @return 查询的结果
     * @author 闫伟强
     * @version 1.0.0
     * @since 2019-08-16 08:47:57
     */
    @ApiOperation(value = "查询所有Word")
    @GetMapping(value = {"/selectAll"})
    public ItooResult selectAll() {
        List<WordModel> wordModel = wordListService.selectAll();
        return ItooResult.build(ItooResult.SUCCESS, "查询成功", wordModel);
    }

    /**
     * 根据目录结构插入单词
     *
     * @param path 文件路径
     * @return true/false
     * @author 陈广晗
     * @since 2019-08-30 10:05:18
     */
    @ApiOperation(value = "根据目录结构插入单词")
    @GetMapping(value = "/batchInsert")
    public ItooResult batchInsertion(@RequestParam String path) {
        try {
            boolean flag = wordService.batchInsert(path);
            return ItooResult.build(ItooResult.SUCCESS, "上传成功!", flag);
        } catch (Exception e) {
            return ItooResult.build(ItooResult.FAIL, "文件插入失败!");
        }
    }
    /**
     * 删除空值
     *
     * @param path 密码
     * @return true/false
     * @author 陈广晗
     * @since 2019-08-30 10:05:18
     */
    @ApiOperation(value = "删除空值")
    @GetMapping(value = "/deletenull")
    public ItooResult deletenull(@RequestParam String path) {
        try {
            if(path.equals("english")){
                boolean flag = wordService.deleteNull();
                return ItooResult.build(ItooResult.SUCCESS, "操作成功!", flag);
            }
            return ItooResult.build(ItooResult.SUCCESS, "操作失败!");
        } catch (Exception e) {
            return ItooResult.build(ItooResult.FAIL, "操作失败!");
        }
    }
}
