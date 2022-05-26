package com.company.repository;

import com.company.cards.StandardCard;
import com.company.config.DatabaseConfiguration;

import java.sql.*;

public class StandardCardsRepositoryUsingStatements {

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS standard_cards " +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "userUniqueId int, " +
                "cardNumber varchar (16)," +
                "expirationDate DATE," +
                "amount DOUBLE," +
                "withdrawFee DOUBLE)";


        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertStandardCard(StandardCard standardCard) {
        String insertPersonSql = "INSERT INTO standard_cards(userUniqueId, cardNumber, expirationDate, amount, withdrawFee) VALUES(?, ?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql)) {
            preparedStatement.setLong(1, standardCard.getUserUniqueId());
            preparedStatement.setString(2, standardCard.getCardNumber());
            preparedStatement.setDate(3, (Date) standardCard.getExpirationDate());
            preparedStatement.setDouble(4, standardCard.getAmount());
            preparedStatement.setDouble(5, standardCard.getWithdrawFee());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}