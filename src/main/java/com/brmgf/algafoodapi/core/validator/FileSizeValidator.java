package com.brmgf.algafoodapi.core.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

    private DataSize maxSize;

    @Override
    public void initialize(FileSize constraintAnnotation) {
        this.maxSize = DataSize.parse(constraintAnnotation.max());
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
        return multipartFile == null || multipartFile.getSize() <= this.maxSize.toBytes();
    }
}
