package com.example.demo.services;

import com.example.demo.dto.UserPurchaseSummaryDto;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.PurchaseRepository;
import model.User;
import model.Purchase;
import model.Product;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    PurchaseRepository purchaseRepository;

    public byte[] generateUserPurchaseReport() throws Exception {
        //pravim dto
        List<UserPurchaseSummaryDto> reportData = userRepository.findAll().stream()
                .filter(u -> !u.isAdmin()) // Exclude admins
                .map(this::buildSummary)
                .collect(Collectors.toList());

        
        InputStream reportStream = getClass().getResourceAsStream("/reports/userPurchase.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportData);

        // dodaj parametre
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("reportTitle", "Users and Purchases Report");
        parameters.put("generatedOn", new Date());

        // popuni report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

       // u pdf
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    private UserPurchaseSummaryDto buildSummary(User user) {
        List<Purchase> purchases = purchaseRepository.findByUser_IdUser(user.getIdUser());

        int totalSpent = purchases.stream()
                .mapToInt(p -> {
                    Product prod = p.getProduct();
                    return prod != null ? prod.getPrice() : 0;
                })
                .sum();

        Date lastPurchase = purchases.stream()
                .map(Purchase::getTimestamp)
                .max(Date::compareTo)
                .orElse(null);

        return new UserPurchaseSummaryDto(
                user.getUsername(),
                user.getEmail(),
                purchases.size(),
                lastPurchase,
                totalSpent
        );
    }
}
