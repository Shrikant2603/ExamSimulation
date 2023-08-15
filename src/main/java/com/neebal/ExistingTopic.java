package com.neebal;

import com.neebal.entities.Question;
import com.neebal.entities.QuestionOptions;
import com.neebal.entities.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

public class ExistingTopic {
    public static void main(String[] args) {

        QuestionOptions opt1 = new QuestionOptions("A. Sharad Pawar",false);
        QuestionOptions opt2 = new QuestionOptions("B. Balasaheb Thackeray",true);
        QuestionOptions opt3 = new QuestionOptions("C. Sonia Gandhi",false);
        QuestionOptions opt4 = new QuestionOptions("D. Mamta Banerjee",false);

        Question question = new Question("Who is the founder of Shivsena?",5);

        opt1.setQuestion(question);
        opt2.setQuestion(question);
        opt3.setQuestion(question);
        opt4.setQuestion(question);

        Set<QuestionOptions> questionOptionSet = new HashSet<>();
        questionOptionSet.add(opt1);
        questionOptionSet.add(opt2);
        questionOptionSet.add(opt3);
        questionOptionSet.add(opt4);
        question.setQuestion_optionSet(questionOptionSet);

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()){
            Topic topic = session.get(Topic.class,3l);
            Transaction transaction = session.beginTransaction();
            question.setTopic(topic);
            session.save(question);
            transaction.commit();
        }
    }
}
