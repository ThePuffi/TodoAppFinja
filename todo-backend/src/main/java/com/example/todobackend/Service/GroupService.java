package com.example.todobackend.Service;

import com.example.todobackend.Entity.Group;
import com.example.todobackend.Repository.GroupRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {


    private final GroupRepository groupRepository;


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
        throw new EntityNotFoundException("Entity not found");
    }

    public Group getGroup(long id) {
        Optional<Group> groupOptional = groupRepository.findById(id);
        if (groupOptional.isPresent()) {
            return groupOptional.get();
        }
        throw new EntityNotFoundException("Entity not found");
    }

    public void deleteGroup(long id) {
        Optional<Group> groupOptional = groupRepository.findById(id);
        if (groupOptional.isPresent()) {
            groupRepository.deleteById(id);
            return;
        }
        throw new EntityNotFoundException("Entity not found");
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }
}
