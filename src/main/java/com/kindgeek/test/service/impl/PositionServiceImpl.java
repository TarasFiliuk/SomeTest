package com.kindgeek.test.service.impl;

import com.kindgeek.test.exeption.TestNotFoundExeption;
import com.kindgeek.test.entity.Position;
import com.kindgeek.test.repository.PositionRepository;
import com.kindgeek.test.service.PositionService;
import com.kindgeek.test.service.request.PersonPositionDepartmentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class PositionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;

    private Position save(Position position) {
        return positionRepository.save(position);
    }

    @Override
    public Position createPosition() {
        Position position = new Position();
        position.setNamePosition("Junior Java Developer");
        log.info("Position successfully created.");
        return positionRepository.save(position);
    }

    @Override
    public Position getPosition(int positionId) {
        return positionRepository.findById(positionId)
                .orElseThrow(() -> new TestNotFoundExeption("Position", format("Position with ID %s not found", positionId)));
    }

    @Override
    public void deletePosition(int positionId) {
        positionRepository.deleteById(positionId);
        log.info("Position with ID {} deleted",positionId);
    }

    @Override
    public Position updatePosition(int positionId, PersonPositionDepartmentRequest request) {
        Position position = this.getPosition(positionId);
        ofNullable(request.getPositionName()).ifPresent(position::setNamePosition);
        log.info("Position with ID {} updated",positionId);
        return this.save(position);

    }


}
