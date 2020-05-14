package ${basePackage}.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import ${basePackage}.core.Result;
import ${basePackage}.core.ResultGenerator;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* Created by ${author} on ${date}.
*/
@Api(value = "/${modelNameUpperCamel}Controller", tags = "${modelNameUpperCamel}API接口")
@RestController
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {

    @Autowired
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @ApiOperation(value = "插入", notes="入参id不用传")
	@RequestMapping(value = "/add${modelNameUpperCamel}", method = RequestMethod.POST)
	@ResponseBody
    public Result add(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "删除", notes="根据id删除")
	@RequestMapping(value = "/delete${modelNameUpperCamel}", method = RequestMethod.GET)
	@ResponseBody
    public Result delete(@ApiParam("id") @RequestParam Integer id) {
        ${modelNameLowerCamel}Service.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改", notes="根据id修改")
	@RequestMapping(value = "/update${modelNameUpperCamel}", method = RequestMethod.POST)
	@ResponseBody
    public Result update(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value="查询详情", notes="根据id查询")
	@RequestMapping(value = "/selectDetailById",method = RequestMethod.GET)
	@ResponseBody
    public Result detail(@ApiParam("id") @RequestParam Integer id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.findById(id);
        return ResultGenerator.genSuccessResult(${modelNameLowerCamel});
    }

    @ApiOperation(value="分页列表", notes="分页查询、根据某些字段查询")
    @RequestMapping(value = "/selectList",method = RequestMethod.POST)
	@ResponseBody
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
