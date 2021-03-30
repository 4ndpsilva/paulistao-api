package com.aps.paulistao.api.task;

import com.aps.paulistao.api.service.ScraperService;
import com.aps.paulistao.api.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Configuration
@EnableScheduling
public class PartidaTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(PartidaTask.class);

    private static final String TIME_ZONE        = "America/Sao_Paulo";
    private static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

    private final ScraperService scraperService;


    @Scheduled(cron = "0/30 * 00-06 * * TUE", zone = TIME_ZONE)
    public void executeTue(){
        initScheduler("executeTue()");
    }

    @Scheduled(cron = "0/30 * 19-23 * * WED", zone = TIME_ZONE)
    public void executeWed(){
        initScheduler("executeWed()");
    }

    @Scheduled(cron = "0/30 * 19-23 * * THU", zone = TIME_ZONE)
    public void executeThu(){
        initScheduler("executeThu()");
    }

    @Scheduled(cron = "0/30 * 16-23 * * SAT", zone = TIME_ZONE)
    public void executeSat(){
        initScheduler("executeSat()");
    }

    @Scheduled(cron = "0/30 * 11-13 * * SUN", zone = TIME_ZONE)
    public void executeSun1(){
        initScheduler("executeSun1()");
    }

    @Scheduled(cron = "0/30 * 16-23 * * SAT", zone = TIME_ZONE)
    public void executeSun2(){
        initScheduler("executeSun2()");
    }

    public void initScheduler(final String diaSemana){
        this.saveLogInfo(String.format("%s: %s", diaSemana, DateUtil.formatDate(LocalDateTime.now(), DATE_TIME_FORMAT)));
        scraperService.verificarPartidasPorPeriodo();
    }

    private void saveLogInfo(final String message){
        LOGGER.info(message);
    }
}