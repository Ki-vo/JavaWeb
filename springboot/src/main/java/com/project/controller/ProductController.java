package com.project.controller;

import com.project.anno.MyLog;
import com.project.pojo.Product;
import com.project.pojo.Result;
import com.project.service.FileService;
import com.project.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
public class ProductController {
    private static final String COVER_IMAGE_DIR
            = System.getProperty("user.dir") + File.separator + "cover_image" + File.separator;

    @Autowired
    private ProductService productService;

    @Autowired
    private FileService fileService;

    @MyLog
    @PostMapping("/product/add")
    public Result addProduct(@RequestParam("name") String name,
                             @RequestParam("cate_id") Integer cateId,
                             @RequestParam("seller") String seller,
                             @RequestParam("price") Integer price,
                             @RequestParam("rest") Integer rest,
                             @RequestParam("cover_img") MultipartFile coverImg) {
        try {
            //保存封面图
            String coverImgName = fileService.saveImg(coverImg, COVER_IMAGE_DIR);
            //填充product信息
            Product product = new Product(name, cateId, seller, price, rest, coverImgName);
            //调用service
            log.info("商品添加:{}", product);
            productService.addProduct(product);
            return Result.success();
        } catch (Exception e) {
            return Result.error("商品添加失败");
        }
    }

    @MyLog
    @PostMapping("/product/update")
    public Result editProduct(@RequestParam("id") Integer id,
                              @RequestParam("name") String name,
                              @RequestParam("seller") String seller,
                              @RequestParam("price") Integer price,
                              @RequestParam("rest") Integer rest,
                              @RequestParam("cover_img") Object coverImg) {
        Product product = new Product();
        if (coverImg instanceof MultipartFile) {
            //删除原封面图
            try {
                String oldCoverImg = productService.getImgById(id);
                fileService.deleteImg(COVER_IMAGE_DIR + oldCoverImg);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            //保存新封面图
            try {
                MultipartFile file = (MultipartFile) coverImg;
                String newCoverImg = fileService.saveImg(file, COVER_IMAGE_DIR);
                product.setCoverImg(newCoverImg);
            } catch (Exception e) {
                log.error("封面图片保存失败");
                product.setCoverImg("");
            }
        } else {
            //维持原来封面图路径
            product.setCoverImg((String) coverImg);
        }

        //填充product信息
        product.setId(id);
        product.setName(name);
        product.setSeller(seller);
        product.setPrice(price);
        product.setRest(rest);
        log.info("商品信息修改:{}", product);
        //调用service
        try {
            productService.updateProduct(product);
            return Result.success();
        } catch (Exception e) {
            return Result.error("商品修改失败");
        }
    }

    @PostMapping("/product/list")
    public Result getAllProduct() {
        try {
            List<Product> list = productService.getAllProduct();
            return Result.success(list);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/product/type")
    public Result getProductByType(@RequestParam("type") Integer type) {
        try {
            List<Product> list = productService.getProductByType(type);
            return Result.success(list);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/product/hot")
    public Result getHotProduct() {
        try {
            List<Product> list = productService.getHotProduct();
            return Result.success(list);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/product")
    public Result getById(@RequestParam("id") Integer id) {
        Product res = productService.getProductById(id);
        if (res != null) {
            return Result.success(res);
        } else {
            return Result.error("不存在该商品");
        }
    }

    @MyLog
    @GetMapping("/product/del")
    public Result delProductById(@RequestParam("id") Integer id) {
        try {
            //删除封面文件
            String img = productService.getImgById(id);
            fileService.deleteImg(COVER_IMAGE_DIR + img);
            //删除商品数据
            productService.deleteProductById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("删除失败:" + e.getMessage());
        }
    }


}