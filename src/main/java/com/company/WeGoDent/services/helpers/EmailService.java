package com.company.WeGoDent.services.helpers;

import com.company.WeGoDent.models.AppointmentStatus;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;


@Service
public class EmailService {




    @Autowired
    private  JavaMailSender javaMailSender;


    public void sendAuthMessage(
            String to, String subject, String name, String password) throws MessagingException {

        String emailContent = generateEmailContent(name, password);

//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("noreply@wegodent.com");
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(emailContent);


        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(emailContent, true);


        javaMailSender.send(message);

    }

    public void sendAppointmentMessageToPatient(String to,
                                               String subject,
                                               String doctorName, String patientName, LocalDateTime appointmentDateTime,
                                               String location, AppointmentStatus appointmentStatus)
            throws MessagingException
    {
        String emailContent = generateAppointmentMessageToPatient(
                doctorName,patientName,appointmentDateTime,location,appointmentStatus
        );

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(emailContent, true);


        javaMailSender.send(message);
    }



    private String generateAppointmentMessageToPatient(String doctorName, String patientName, LocalDateTime appointmentDateTime,
                                                String location, AppointmentStatus appointmentStatus){


        // Format appointment date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = appointmentDateTime.format(formatter);

        // Create email content
        String emailContent = "";
        if(appointmentStatus == AppointmentStatus.REQUESTED){
            emailContent = "<html>" +
                    "<body>" +
                    "<h2>Appointment is <u><b>Requested</u><b/> </h2>" +
                    "<p>Dear " + patientName + ",</p>" +
                    "<p>Your appointment with " + doctorName + " is sent to doctor.</p>" +
                    "<p>Appointment details:</p>" +
                    "<ul>" +
                    "<li>Date and Time: " + formattedDateTime + "</li>" +
                    "<li>Location: " + location + "</li>" +
                    "</ul>" +
                    "<p>You will be informed once " + doctorName + " approves your appointment</p>" +
                    "<p>Best regards,</p>" +
                    "<p>WeGoDent Inc.</p>" +
                    "</body>" +
                    "</html>";
        } else if (appointmentStatus == AppointmentStatus.SCHEDULED){
            emailContent = "<html>" +
                    "<body>" +
                    "<h2>Appointment is <u><b>APPROVED</u><b/> </h2>" +
                    "<p>Dear " + patientName + ",</p>" +
                    "<p>Your appointment with " + doctorName + " is approved by doctor.</p>" +
                    "<p>Appointment details:</p>" +
                    "<ul>" +
                    "<li>Date and Time: " + formattedDateTime + "</li>" +
                    "<li>Location: " + location + "</li>" +
                    "</ul>" +
                    "<p>Please arrive on time and bring any necessary documents or medical records.</p>" +
                    "<p>Best regards,</p>" +
                    "<p>WeGoDent Inc. </p>" +
                    "</body>" +
                    "</html>";
        } else if (appointmentStatus == AppointmentStatus.REJECTED){
            emailContent = "<html>" +
                    "<body>" +
                    "<h2>Unfortunately, Appointment is <u><b>REJECTED</u><b/> </h2>" +
                    "<p>Dear " + patientName + ",</p>" +
                    "<p>Your appointment with " + doctorName + " is rejected by doctor.</p>" +
                    "<p>Appointment details:</p>" +
                    "<ul>" +
                    "<li>Date and Time: " + formattedDateTime + "</li>" +
                    "<li>Location: " + location + "</li>" +
                    "</ul>" +
                    "<p>We are sorry for any kind of inconvenience.</p>" +
                    "<p>Best regards,</p>" +
                    "<p>WeGoDent Inc. </p>" +
                    "</body>" +
                    "</html>";
        } else if (appointmentStatus == AppointmentStatus.CANCELLED){
            emailContent = "<html>" +
                    "<body>" +
                    "<h2>Unfortunately, Appointment is <u><b>REJECTED</u><b/> </h2>" +
                    "<p>Dear " + patientName + ",</p>" +
                    "<p>Your appointment with " + doctorName + " is rejected by doctor.</p>" +
                    "<p>Appointment details:</p>" +
                    "<ul>" +
                    "<li>Date and Time: " + formattedDateTime + "</li>" +
                    "<li>Location: " + location + "</li>" +
                    "</ul>" +
                    "<p>We are sorry for any kind of inconvenience.</p>" +
                    "<p>Best regards,</p>" +
                    "<p>WeGoDent Inc. </p>" +
                    "</body>" +
                    "</html>";
        } else if (appointmentStatus == AppointmentStatus.COMPLETED){
            emailContent = "<html>" +
                    "<body>" +
                    "<h2>Appointment is <u><b>COMPLETED</u><b/> </h2>" +
                    "<p>Dear " + patientName + ",</p>" +
                    "<p>Your appointment with " + doctorName + " is completed.</p>" +
                    "<p>Appointment details:</p>" +
                    "<ul>" +
                    "<li>Date and Time: " + formattedDateTime + "</li>" +
                    "<li>Location: " + location + "</li>" +
                    "</ul>" +
                    "<p>Thank you for choosing our service, we hope you enjoyed the process and your health is better." +
                    "Within the realm of healing, compassion's warmth and understanding wield power beyond the surgeon's blade or the chemist's potion</p>" +


                    "<p>Best regards,</p>" +
                    "<p>WeGoDent Inc. </p>" +
                    "</body>" +
                    "</html>";
        }
        return emailContent.toString();
    }

    public void sendAppointmentMessageToDoctor(String to, String subject, String doctorName,
                                               String patientName,
                                               LocalDateTime appointmentDateTime,
                                               String location){

        String emailContent = generateDoctorNotificationConTent(doctorName,patientName,appointmentDateTime,location);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(emailContent);
        javaMailSender.send(message);

    }

    private String generateDoctorNotificationConTent(String doctorName,
                                                     String patientName,
                                                     LocalDateTime appointmentDateTime,
                                                     String location){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = appointmentDateTime.format(formatter);

        // Create email content
        String emailContent = "<html>" +
                "<body>" +
                "<h2>Appointment Notification</h2>" +
                "<p>Dear " + doctorName + ",</p>" +
                "<p>You have an upcoming appointment with patient " + patientName + ".</p>" +
                "<p>Appointment details:</p>" +
                "<ul>" +
                "<li>Date and Time: " + formattedDateTime + "</li>" +
                "<li>Location: " + location + "</li>" +
                "</ul>" +
                "<p>Please either accept or decline the appointment.</p>" +
                "<p>Best regards,</p>" +
                "<p>WeGoDent Inc.</p>" +
                "</body>" +
                "</html>";

        return emailContent.toString();
    }

    private String generateEmailContent(String name, String password) {
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("Dear ").append(name).append(",\n\n");
        emailContent.append("Thank you for registering.\n\n");
        emailContent.append("Here are your login details:\n\n");
        emailContent.append("Name: ").append(name).append("\n");
        emailContent.append("Password: ").append(password).append("\n\n");
        emailContent.append("Please keep this information confidential and do not share it with anyone.\n\n");
        emailContent.append("Best regards,\n");
        emailContent.append("Your Application");

        return emailContent.toString();
    }



}
