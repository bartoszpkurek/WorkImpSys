package pl.kwmm.wis.web.utils;

import pl.kwmm.wis.model.Employee;
import pl.kwmm.wis.model.Notification;

/**
 * Class with method for counting the points received from completing the
 * Notification status.
 *
 * @author Bartosz Kurek
 * @version 1.0
 * @since 2019-07-13
 */
public class PointsUtils {

    public static int countPoints(Notification n) {
        final int completedPoints = 10;
        int categoryPoints = 0;

        Notification.NotificationCategory category = n.getCategory();
        switch (category) {
            case Other:
                categoryPoints = 5;
                break;
            case Workspace:
                categoryPoints = 10;
                break;
            case Organization:
                categoryPoints = 15;
                break;
            case Process:
                categoryPoints = 20;
                break;
        }
        
        int priorityPoints = 0;
        int priority = n.getPriority();
        switch (priority) {
            case 1:
                priorityPoints = 25;
                break;
            case 2:
                priorityPoints = 20;
                break;
            case 3:
                priorityPoints = 15;
                break;
            case 4:
                priorityPoints = 10;
                break;
            case 5:
                priorityPoints = 5;
                break;
        }
        boolean moneyrequired = n.isMoneyrequired();

        if (moneyrequired) {
            int points = completedPoints + categoryPoints + priorityPoints;
            return points;
        } else {
            int points = completedPoints + categoryPoints + priorityPoints + 20;
            return points;
        }
    }
}
