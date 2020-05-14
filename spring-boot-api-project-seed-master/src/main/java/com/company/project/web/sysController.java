package com.company.project.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.sys;
import com.company.project.service.sysService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by LX on 2020-03-23.
*/
@Api(value = "/sysController", tags = "sysAPI接口")
@RestController
@RequestMapping("/sys")
public class sysController {

    @Resource
    private sysService sysService;

    @ApiOperation(value = "插入", notes="入参id不用传")
	@RequestMapping(value = "/addsys", method = RequestMethod.POST)
	@ResponseBody
    public Result add(@RequestBody sys sys) {
        sysService.save(sys);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "删除", notes="根据id删除")
	@RequestMapping(value = "/deletesys", method = RequestMethod.GET)
	@ResponseBody
    public Result delete(@ApiParam("id") @RequestParam Integer id) {
        sysService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改", notes="根据id修改")
	@RequestMapping(value = "/updatesys", method = RequestMethod.POST)
	@ResponseBody
    public Result update(@RequestBody sys sys) {
        sysService.update(sys);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="查询详情", notes="根据id查询")
	@RequestMapping(value = "/selectDetailById",method = RequestMethod.GET)
	@ResponseBody
    public Result detail(@ApiParam("id") @RequestParam Integer id) {
        sys sys = sysService.findById(id);
        return ResultGenerator.genSuccessResult(sys);
    }

    @ApiOperation(value="分页列表", notes="分页查询、根据某些字段查询")
    @RequestMapping(value = "/selectList",method = RequestMethod.POST)
	@ResponseBody
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<sys> list = sysService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
