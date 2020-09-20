package DBMethods;

import Entities.Login;
import Entities.MessageEntity;
import org.apache.logging.log4j.LogManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class GetMessagesClass {
    private static final String PERSISTENT_UNIT_NAME = "UnitName";
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(GetMessagesClass.class);
    private String userLogin;
    private String hisFriend;
    public GetMessagesClass(String userLogin, String hisFriend){
        this.userLogin = userLogin;
        this.hisFriend = hisFriend;
    };

    public List<MessageEntity> getMessagesMethod(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        logger.info("Method works");
        TypedQuery<MessageEntity> queryMsg = entityManager.createQuery("SELECT m FROM MessageEntity m WHERE" +
                " m.userLogin.userLogin IN (:userLoginParam, :hisFriendParam) AND m.hisFriend.userLogin IN (:userLoginParam," +
                " :hisFriendParam)", MessageEntity.class);

        logger.warn(queryMsg + " okk");
        logger.warn(userLogin + " gg " + hisFriend);
        queryMsg.setParameter("userLoginParam", userLogin);
        queryMsg.setParameter("hisFriendParam", hisFriend);
        return queryMsg.getResultList();
    }
}