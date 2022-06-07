package com.TsoyDmitriy.FitAuth.service;

import com.TsoyDmitriy.FitAuth.model.Role;
import com.TsoyDmitriy.FitAuth.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepo roleRepo;

    public Role findById(Long id) {
        return roleRepo.findById(id).get();
    }
}
