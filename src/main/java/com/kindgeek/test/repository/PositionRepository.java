package com.kindgeek.test.repository;

import com.kindgeek.test.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position,Integer> {

}
