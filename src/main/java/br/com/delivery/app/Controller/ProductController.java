package br.com.delivery.app.Controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.delivery.app.Model.Product;
import br.com.delivery.app.Repository.CategoryRepository;
import br.com.delivery.app.Repository.ProductRepository;
import br.com.delivery.app.Util.FileUploadUtil;

@Controller
public class ProductController implements WebMvcConfigurer{

	@Autowired  
	private ProductRepository repository_product;
	
	@Autowired  
	private CategoryRepository repository_category;
	
	 //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "photos";
	
	@GetMapping({"/products"})
	public String  getAllProducts(Model model){
		model.addAttribute("products", repository_product.findAll());
		return "list-product";
	}
	
	@GetMapping("/new-product")
	public String showProductForm(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("categorys", repository_category.findAll());
	    return "add-product";
	}
	 
	@PostMapping("/new-product")
    public String addProduct(@Valid @ModelAttribute("product") Product product, 
    		                 @RequestParam("image") MultipartFile multipartFile,
    		                 BindingResult result, 
    		                 Model model) throws IOException {
		
		if (result.hasErrors()) 
		{
			model.addAttribute("error", result.getAllErrors());
			return "add-product";
		}
		
		if (!multipartFile.isEmpty())
		{
			 String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			 product.setPhoto(fileName);
		         
		     String uploadDir = UPLOADED_FOLDER;
		 
		     FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		}
		
	     repository_product.save(product);
     return "redirect:/products";
    }
	
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Product product = repository_product.findById(id).orElseThrow(() -> new IllegalArgumentException("produto não encontrado !"));
	    
	    model.addAttribute("product", product);
	    model.addAttribute("categorys", repository_category.findAll());
	    return "edit-product";
	}
	
	@PostMapping("/update/{id}")
	public String updateProduct(@PathVariable("id") long id, Product product,@RequestParam("image") MultipartFile multipartFile, Model model) throws IOException {
		
		if (!multipartFile.isEmpty())
		{
			 String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			 product.setPhoto(fileName);
		         
		     String uploadDir = UPLOADED_FOLDER;
		 
		     FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		}
		
		repository_product.update(id,product.getName(), 
					                 product.getPrice() ,
					                 product.getPromotion_price(),
					                 product.getDescription(), 
					                 product.getCategory_id(),
					                 product.getPhoto());
		
		
	    return "redirect:/products";
	}
	   
	
	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable("id") long id, Model model) {
		Product product = repository_product.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Product Id:" + id));
		repository_product.delete(product);
	    return "redirect:/products";
	}
}
