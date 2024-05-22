package com.example.todobackend.Service;

import com.example.todobackend.DTO.GroupDTO;
import com.example.todobackend.Entity.Group;
import com.example.todobackend.Mapper.GroupMapper;
import com.example.todobackend.Repository.GroupRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {


    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    private final String doesNotExist = "Group does not exit";

    public Group addGroup(Group group) {
        Optional<Group> groupOptional = groupRepository.findById(group.getId());
        if (groupOptional.isPresent()) {
            throw new IllegalArgumentException("Group already exists");
        }
        return groupRepository.save(group);
    }

    public Group editGroup(Group group) {
        Optional<Group> groupOptional = groupRepository.findById(group.getId());
        if (groupOptional.isPresent()) {
            return groupRepository.save(group);
        }
        throw new EntityNotFoundException(doesNotExist);
    }

    public Group getGroup(long id) {
        Optional<Group> groupOptional = groupRepository.findById(id);
        if (groupOptional.isPresent()) {
            return groupOptional.get();
        }
        throw new EntityNotFoundException(doesNotExist);
    }

    public void deleteGroup(long id) {
        Optional<Group> groupOptional = groupRepository.findById(id);
        if (groupOptional.isPresent()) {
            groupRepository.deleteById(id);
            return;
        }
        throw new EntityNotFoundException(doesNotExist);
    }

    public List<GroupDTO> getAllGroups() {
        List<GroupDTO> groups = new ArrayList<>();
        groupRepository.findAll().forEach(group -> {
            groups.add(groupMapper.entityToDto(group));
        });
        return groups;
    }
}
