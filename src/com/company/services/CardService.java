package com.company.services;

import com.company.cards.PremiumCard;
import com.company.cards.StandardCard;

import java.text.ParseException;
import java.util.Date;

public class CardService {
    public StandardCard createStandardCard(long userUniqueId, String cardNumber, Date expirationDate, double ammount, double withdrawFee) throws ParseException
    {
        return new StandardCard(userUniqueId, cardNumber, expirationDate, ammount, withdrawFee);
    }

    public PremiumCard createPremiumCard(long userUniqueId, String cardNumber, Date expirationDate, double ammount, double cashBack) throws ParseException
    {
        return new PremiumCard(userUniqueId, cardNumber, expirationDate, ammount, cashBack);
    }


}
