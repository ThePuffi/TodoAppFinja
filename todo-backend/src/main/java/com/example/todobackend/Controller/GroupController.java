package com.example.todobackend.Controller;

import com.example.todobackend.DTO.GroupDTO;
import com.example.todobackend.Service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("api/group")
@CrossOrigin
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/addGroup")
    public ResponseEntity<GroupDTO> addGroup(@RequestBody GroupDTO group) {
        return new ResponseEntity<>(this.groupService.addGroup(group), HttpStatus.CREATED);
    }
    @GetMapping("/getAllGroups")
    public ResponseEntity<List<GroupDTO>> getAllGroups() {
        return new ResponseEntity<>(this.groupService.getAllGroups(), HttpStatus.FOUND);
    }
    @GetMapping("/findGroupById")
    public ResponseEntity<GroupDTO> findGroupById(@RequestParam Long groupId) {
        return new ResponseEntity<>(this.groupService.findGroupById(groupId), HttpStatus.FOUND);
    }
    @PutMapping("/updateGroup")
    public ResponseEntity<GroupDTO> updateGroup(@RequestBody GroupDTO group) {
        return new ResponseEntity<>(this.groupService.updateGroup(group), HttpStatus.OK);
    }

    @DeleteMapping("/deleteGroup")
    public ResponseEntity<HttpStatus> deleteGroup(@RequestBody GroupDTO group) {
        this.groupService.deleteGroup(group);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
