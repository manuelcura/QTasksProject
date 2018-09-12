package Domain;

import Domain.TaskTag;
import Domain.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-12T16:22:05")
@StaticMetamodel(Task.class)
public class Task_ { 

    public static volatile SingularAttribute<Task, Date> date;
    public static volatile SingularAttribute<Task, String> description;
    public static volatile SingularAttribute<Task, Integer> id;
    public static volatile SingularAttribute<Task, String> title;
    public static volatile SingularAttribute<Task, Integer> priority;
    public static volatile SingularAttribute<Task, Boolean> done;
    public static volatile SingularAttribute<Task, User> userId;
    public static volatile SingularAttribute<Task, TaskTag> tags;

}