package com.ruoyi.project.system.product.controller;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.JSON;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.product.domain.Product;
import com.ruoyi.project.system.product.service.IProductService;
import com.ruoyi.project.system.role.domain.Role;
import com.ruoyi.project.system.role.service.IRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色信息
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/product")
public class ProductController extends BaseController
{

    private String prefix = "system/product";

    @Autowired
    private IProductService productService;
    
    @RequiresPermissions("system:product:view")
    @GetMapping()
    public String product()
    {
        return prefix + "/product";
    }

    @RequiresPermissions("system:product:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Product product)
    {
        setPageInfo(product);
        List<Product> list = productService.selectProductAll();
        System.out.println(list);
        return getDataTable(list);
    }
    
    /**
     *
     * 新增角色
     */
    @RequiresPermissions("system:product:add")
    @Log(title = "系统管理", action = "产品管理-新增产品")
    @GetMapping("/add")
    public String add(Model model)
    {
        return prefix + "/add";
    }

    /**
     * 修改角色
     */
    @RequiresPermissions("system:product:edit")
    @Log(title = "系统管理", action = "产品管理-修改产品")
    @GetMapping("/edit/{roleId}")
    public String edit(@PathVariable("roleId") Long productId, Model model)
    {
        Product product = productService.selectProductById(productId);
        model.addAttribute("product", product);
        return prefix + "/edit";
    }

    /**
     * 保存角色
     */
    @RequiresPermissions("system:product:save")
    @Log(title = "系统管理", action = "产品管理-保存产品")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(Product product)
    {
        if (productService.saveProduct(product) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @RequiresPermissions("system:product:remove")
    @Log(title = "系统管理", action = "产品管理-删除产品")
    @RequestMapping("/remove/{roleId}")
    @ResponseBody
    public JSON remove(@PathVariable("roleId") Long productId)
    {
        Product product = productService.selectProductById(productId);
        if (product == null)
        {
            return JSON.error("产品不存在");
        }
        if (productService.deleteProductById(productId) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @RequiresPermissions("system:role:batchRemove")
    @Log(title = "系统管理", action = "产品管理-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = productService.batchDeleteProduct(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    /**
     * 选择菜单树
     */
    @GetMapping("/selectMenuTree")
    public String selectMenuTree()
    {
        return prefix + "/tree";
    }

}