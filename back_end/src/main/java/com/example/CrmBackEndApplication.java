package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.model.mapper")
@SpringBootApplication
public class CrmBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmBackEndApplication.class, args);
        /*System.out.println("       启动成功                 \n" +
                "    '----'        ||      //        \n" +
                "  //      \\\\      ||    //      \n" +
                " ||        ||     ||   //          \n" +
                " ||        ||     ||   \\\\           \n" +
                "  \\\\      //      ||    \\\\             \n" +
                "    '----'        ||      \\\\              ");*/
        System.out.println("  _          _ _                         _     _ \n" +
                " | |        | | |                       | |   | |\n" +
                " | |__   ___| | | _____      _____  _ __| | __| |\n" +
                " | '_ \\ / _ \\ | |/ _ \\ \\ /\\ / / _ \\| '__| |/ _` |\n" +
                " | | | |  __/ | | (_) \\ V  V / (_) | |  | | (_| |\n" +
                " |_| |_|\\___|_|_|\\___( )_/\\_/ \\___/|_|  |_|\\__,_|\n" +
                "                     |/                          \n" +
                "                                                 ");
    }

}
