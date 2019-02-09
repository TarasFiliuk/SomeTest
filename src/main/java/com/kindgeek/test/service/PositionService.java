package com.kindgeek.test.service;

import com.kindgeek.test.entity.Position;
import com.kindgeek.test.service.request.PositionRequest;

public interface PositionService {
    Position createPosition();
    Position getPosition(int positionId);
    void deletePosition(int positionId);
    Position updatePosition(int positionId, PositionRequest positionRequest);
}
