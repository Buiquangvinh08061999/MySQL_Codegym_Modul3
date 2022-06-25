package com.cg.service;

import com.cg.dto.UserDTO;
import com.cg.model.User;

import java.util.List;

public interface IUserService extends IGeneralService<User> {

    List<UserDTO> findAllUserDTO();
}
