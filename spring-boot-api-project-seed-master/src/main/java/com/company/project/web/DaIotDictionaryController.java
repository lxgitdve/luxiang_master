package com.company.project.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.DaIotDictionary;
import com.company.project.service.DaIotDictionaryService;
import com.company.project.util.RedisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
* Created by LX on 2020-04-16.
*/
@Api(value = "/DaIotDictionaryController", tags = "DaIotDictionaryAPI接口")
@RestController
@RequestMapping("/da/iot/dictionary")
public class DaIotDictionaryController {

    @Autowired
    private DaIotDictionaryService daIotDictionaryService;
    
    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "插入", notes="入参id不用传")
	@RequestMapping(value = "/addDaIotDictionary", method = RequestMethod.POST)
	@ResponseBody
    public Result add(@RequestBody DaIotDictionary daIotDictionary) {
        daIotDictionaryService.save(daIotDictionary);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "删除", notes="根据id删除")
	@RequestMapping(value = "/deleteDaIotDictionary", method = RequestMethod.GET)
	@ResponseBody
    public Result delete(@ApiParam("id") @RequestParam Integer id) {
        daIotDictionaryService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改", notes="根据id修改")
	@RequestMapping(value = "/updateDaIotDictionary", method = RequestMethod.POST)
	@ResponseBody
    public Result update(@RequestBody DaIotDictionary daIotDictionary) {
        daIotDictionaryService.update(daIotDictionary);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="查询详情", notes="根据id查询")
	@RequestMapping(value = "/selectDetailById",method = RequestMethod.GET)
	@ResponseBody
    public Result detail(@ApiParam("id") @RequestParam Integer id) {
    	String dataKey = "da_iot_dictionary_" + id;
        Map<String,Object> map = Maps.newHashMap();
        //如果数据存在，直接从redis中获取，否则从数据库查询，再添加到redis中
        if(redisUtil.hasKey(dataKey)){
        	map = redisUtil.hmget2(dataKey);
        }else{
        	DaIotDictionary daIotDictionary = daIotDictionaryService.findById(id);
        	map = BeanUtil.beanToMap(daIotDictionary);
        	long expire = id * 60;
        	redisUtil.hmset(dataKey, map, expire);
        }
        return ResultGenerator.genSuccessResult(map);
    }
    
    @ApiOperation(value="查询所有数据并添加到redis", notes="查询所有数据并添加到redis")
  	@RequestMapping(value = "/selectDetailByNum",method = RequestMethod.GET)
  	@ResponseBody
    public Result selectDetailByNum(@ApiParam("num") @RequestParam Integer num) {
	  	  String dataKey = "da_iot_dictionary_" + num;
	      Map<String,Object> map = Maps.newHashMap();
	      //如果数据存在，直接从redis中获取，否则从数据库查询，再添加到redis中
	      if(redisUtil.hasKey(dataKey)){
	      	map = redisUtil.hmget2(dataKey);
	      }else{
	    	  List<DaIotDictionary> list = daIotDictionaryService.findAll();
	    	  for(int i = 0; i < list.size(); i++){
	    		  DaIotDictionary daIotDictionary = list.get(i);
	    		  dataKey = "da_iot_dictionary_" + daIotDictionary.getId();
	    		  long expire = daIotDictionary.getId() * 60;
	    		  map = BeanUtil.beanToMap(daIotDictionary);
	    		  redisUtil.hmset(dataKey, map, expire);
	    	  }
	      }
	      return ResultGenerator.genSuccessResult(map);
    }

    @ApiOperation(value="分页列表", notes="分页查询、根据某些字段查询")
    @RequestMapping(value = "/selectList",method = RequestMethod.POST)
	@ResponseBody
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<DaIotDictionary> list = daIotDictionaryService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
