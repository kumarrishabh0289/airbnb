package com.database.databasedemo.controller;

import com.database.databasedemo.entity.Asset;
import com.database.databasedemo.repository.AssetSpringDataRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins="*")
@RestController
public class AssetResource {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String secretKey = "openhome";

    @Autowired
    private AssetSpringDataRepo repo;

    @GetMapping("/asset")
    public List<Asset> retriveAsset(@RequestHeader HttpHeaders headers) {
        int y = Integer.parseInt(headers.get("id").get(0));
        List<Asset> asset = repo.findByOwner(y);

        return asset;
    }



}
