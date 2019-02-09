package com.kindgeek.test.service;

import com.kindgeek.test.entity.Position;
import com.kindgeek.test.service.request.PersonPositionDepartmentRequest;

public interface PositionService {
    Position createPosition();
    Position getPosition(int positionId);
    void deletePosition(int positionId);
    Position updatePosition(int positionId, PersonPositionDepartmentRequest request);
}
