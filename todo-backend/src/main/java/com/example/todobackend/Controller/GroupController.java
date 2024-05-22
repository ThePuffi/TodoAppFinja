package com.example.todobackend.Controller;

import com.example.todobackend.Entity.Group;
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
    public ResponseEntity<Group> addGroup(@RequestBody Group group, @RequestParam("memberId") Long memberId) {
        return new ResponseEntity<>(this.groupService.addGroup(group), HttpStatus.CREATED);
    }
    @GetMapping("/getAllGroups")
    public ResponseEntity<List<Group>> getAllGroups() {
        return new ResponseEntity<>(this.groupService.getAllGroups(), HttpStatus.FOUND);
    }
    @GetMapping("/findGroupById")
    public ResponseEntity<Group> findGroupById(@RequestParam Long groupId) {
        return new ResponseEntity<>(this.groupService.getGroup(groupId), HttpStatus.FOUND);
    }
    @PutMapping("/updateGroup")
    public ResponseEntity<Group> updateGroup(@RequestBody Group group) {
        return new ResponseEntity<>(this.groupService.editGroup(group), HttpStatus.OK);
    }

    @DeleteMapping("/deleteGroup")
    public ResponseEntity<HttpStatus> deleteGroup(@RequestParam long groupId) {
        this.groupService.deleteGroup(groupId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
