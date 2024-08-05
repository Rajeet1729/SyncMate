package com.sm.validators;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileValidator implements ConstraintValidator<validFile,MultipartFile> {

    private static final long MAX_FILE_SIZE = 1024*1024*2;


    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if(file==null||file.isEmpty()){
            
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("file cannot be empty").addConstraintViolation();
            return false;
        }
        if(file.getSize()>MAX_FILE_SIZE){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File size should be less 2MB").addConstraintViolation();
            return false;
        }
        // try {
        //     BufferedImage bufferedImage  = ImageIO.read(file.getInputStream());
            
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        return true;
        
    }

}
