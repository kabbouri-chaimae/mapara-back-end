package com.para_space.parapharma_pace;



import com.para_space.parapharma_pace.dao.CategoryRepository;
import com.para_space.parapharma_pace.dao.ProduitRepository;
import com.para_space.parapharma_pace.entities.Category;
import com.para_space.parapharma_pace.entities.Produit;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.Random;


@SpringBootApplication
@ComponentScan(basePackages = {"com.para_space.parapharma_pace.dao"})

public class ParapharmaPaceApplication  extends SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(ParapharmaPaceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        repositoryRestConfiguration.exposeIdsFor(Produit.class,Category.class);

        categoryRepository.save(new Category(null,"Computers",null,null,null));
        categoryRepository.save(new Category(null,"Printers",null,null,null));
        categoryRepository.save(new Category(null,"Smart phones",null,null,null));

    }
}
