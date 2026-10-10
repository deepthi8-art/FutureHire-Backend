package com.futurehire.service;

import com.futurehire.entity.Notification;
import com.futurehire.entity.User;
import com.futurehire.exception.ResourceNotFoundException;
import com.futurehire.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getStudentNotifications(User student) {
        return notificationRepository.findByUserIdOrUserIsNullOrderByCreatedAtDesc(student.getId());
    }

    public List<Notification> getUnreadNotifications(User student) {
        return notificationRepository.findByUserIdAndIsReadFalse(student.getId());
    }

    public Notification markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with id: " + notificationId));
        notification.setRead(true);
        return notificationRepository.save(notification);
    }

    public void markAllAsRead(User student) {
        List<Notification> unread = getUnreadNotifications(student);
        unread.forEach(n -> n.setRead(true));
        notificationRepository.saveAll(unread);
    }

    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}
