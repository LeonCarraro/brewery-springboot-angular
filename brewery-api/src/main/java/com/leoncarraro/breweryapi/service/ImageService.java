package com.leoncarraro.breweryapi.service;

import com.leoncarraro.breweryapi.service.exceptions.FileException;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageService {

    public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {
        String extension = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());

        if (extension == null) {
            throw new FileException("Erro ao tentar obter a extensão da imagem!");
        }
        if (!extension.equals("png") && !extension.equals("jpg")) {
            throw new FileException("Somente imagens PNG e JPG são permitidas!");
        }

        try {
            BufferedImage image = ImageIO.read(uploadedFile.getInputStream());
            if (extension.equals("png")) {
                image = pngToJpg(image);
            }

            return image;
        } catch (IOException e) {
            throw new FileException("Erro ao ler aquivo!");
        }
    }

    public BufferedImage pngToJpg(BufferedImage pngImage) {
        BufferedImage jpgImage = new BufferedImage(pngImage.getWidth(), pngImage.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        jpgImage.createGraphics().drawImage(pngImage, 0, 0, Color.WHITE, null);

        return jpgImage;
    }

    public BufferedImage resize(BufferedImage source, Integer size) {
        return Scalr.resize(source, Scalr.Method.ULTRA_QUALITY, size);
    }

    public InputStream getInputStream(BufferedImage bufferedImage, String extension) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, extension, byteArrayOutputStream);
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new FileException("Erro ao ler arquivo!");
        }
    }

}
