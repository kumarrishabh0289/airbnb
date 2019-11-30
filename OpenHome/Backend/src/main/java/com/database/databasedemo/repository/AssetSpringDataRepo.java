package com.database.databasedemo.repository;

import com.database.databasedemo.entity.Asset;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssetSpringDataRepo extends JpaRepository<Asset, Integer> {
    @Query("SELECT * FROM asset a WHERE a.owner = ?1")
    List<Asset> findByOwner(int Owner);
}
