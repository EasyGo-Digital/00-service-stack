package eu.easygoit.service;

import eu.easygoit.dto.common.NotificationDto;
import eu.easygoit.model.INotifiableEntity;

import java.io.IOException;


/**
 * The interface Notification listener service.
 */
public interface INotificationListenerService {

    /**
     * Perform post persist notification.
     *
     * @param entity the entity
     */
    void performPostPersistNotification(INotifiableEntity entity);

    /**
     * Perform post remove notification.
     *
     * @param entity the entity
     */
    void performPostRemoveNotification(INotifiableEntity entity);

    /**
     * Perform post update notification.
     *
     * @param entity the entity
     */
    void performPostUpdateNotification(INotifiableEntity entity);

    /**
     * Send notification.
     *
     * @param notificationDtos the notification dtos
     * @throws IOException the io exception
     */
    void sendNotification(NotificationDto[] notificationDtos) throws IOException;
}
