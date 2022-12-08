package org.zerock.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.zerock.DTO.AuthorityDTO;
import org.zerock.DTO.UserDTO;

import java.util.List;

public interface MemberService {
    List<UserDTO> getUserList();

    List<AuthorityDTO> getAuthList(String userId);

    void createUser(UserDTO member);

    UserDTO readUserById(String userId);

    UserDTO readUserByName(String userName);

    void deleteUser(String userId);

    boolean disableUser(String userId);

    boolean authorizationToUser(AuthorityDTO auth);

    boolean revokeOneAuthorityToUser(AuthorityDTO auth);

    boolean revokeAllAuthorityToUser(String userId);

    boolean enableUser(String userId);

    boolean hasAdminRole(String userId);

    UserDTO dtoConverter(UserDetails userDetails);

}
