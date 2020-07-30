package com.om.controller;


import java.io.IOException;
import java.util.Base64;
import java.util.Optional;




import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.om.Repo.ImageRepo;
import com.om.model.ImageModel;

@Controller
public class ImageController {
	
	@Autowired
	ImageRepo imageRepo;
	
	@RequestMapping("/")
	public ModelAndView welcome()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("image");
		return mv;
	}
	
	@PostMapping("/upload")
	public String uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
	
		ImageModel img = new ImageModel();     
		img.setName(file.getOriginalFilename());
		img.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		img.setType(file.getContentType());
		imageRepo.save(img);
		return "Done";
	}
	
	@RequestMapping("/show")
	public ModelAndView show()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("show");
		return mv;
	}
	@GetMapping(path = { "/getimage/" })
	public ModelAndView getImage(@RequestParam("imageName") String imageName) throws IOException {
		final Optional<ImageModel> retrievedImage = imageRepo.findByName(imageName);
		ModelAndView mv = new ModelAndView();
		String b = retrievedImage.get().getImage();
		 mv.addObject("im", b);
		 mv.setViewName("showimage");
		 return mv;
	}
}
