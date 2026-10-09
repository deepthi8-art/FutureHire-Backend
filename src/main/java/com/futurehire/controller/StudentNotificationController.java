package com.futurehire.controller;

import com.futurehire.dto.response.ApiResponse;
import com.futurehire.entity.Notification;
import com.futurehire.entity.User;
import com.futurehire.service.AuthService;
import com.futurehire.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/notifications")
public class StudentNotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private AuthService authService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Notification>>> getNotifications() {
        User student = authService.getCurrentAuthenticatedUser();
        List<Notification> notifications = notificationService.getStudentNotifications(student);
        return ResponseEntity.ok(ApiResponse.success("Notifications fetched", notifications));
    }

    @GetMapping("/unread")
    public ResponseEntity<ApiResponse<List<Notification>>> getUnread() {
        User student = authService.getCurrentAuthenticatedUser();
        List<Notification> notifications = notificationService.getUnreadNotifications(student);
        return ResponseEntity.ok(ApiResponse.success("Unread notifications fetched", notifications));
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<ApiResponse<Notification>> markAsRead(@PathVariable Long id) {
        Notification notification = notificationService.markAsRead(id);
        return ResponseEntity.ok(ApiResponse.success("Notification marked as read", notification));
    }

    @PutMapping("/read-all")
    public ResponseEntity<ApiResponse<String>> markAllRead() {
        User student = authService.getCurrentAuthenticatedUser();
        notificationService.markAllAsRead(student);
        return ResponseEntity.ok(ApiResponse.success("All notifications marked as read", "Updated"));
    }
}
