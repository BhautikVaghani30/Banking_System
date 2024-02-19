package com.bank.banksystem.servies;

import com.bank.banksystem.Constants.MSG;
import com.bank.banksystem.dao.AccoutnRepository;
import com.bank.banksystem.entities.Account;
import com.bank.banksystem.entities.Diposit;
import com.bank.banksystem.entities.MoneyTransfer;
import com.bank.banksystem.entities.Withdraw;
import com.bank.banksystem.utilites.bankUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;

@Service
public class AccountServies {

    @Autowired
    AccoutnRepository accoutnRepository;

    public ResponseEntity<String> openAccount(Account account) {
        System.out.println("openacount : " + account);
        try {

            if (validAccount(account)) {

                Account ac = createAccount(account);
                accoutnRepository.save(ac);
                return bankUtils.getResponseEntity(MSG.ACCOUNT_NUMBER + ac.getAccountNumber(), HttpStatus.OK);

            } else {
                return bankUtils.getResponseEntity(MSG.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bankUtils.getResponseEntity(MSG.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Account createAccount(Account account) {

        Account ac = new Account();
        ac.setAccountNumber(generateAccountNumber());
        ac.setName(account.getName());
        ac.setAddress(account.getAddress());
        ac.setContectNumber(account.getContectNumber());
        ac.setEmail(account.getEmail());

        if (account.getBalance() != null) {
            ac.setBalance(account.getBalance());
        } else {
            ac.setBalance("0");
        }

        return ac;

    }

    public static String generateAccountNumber() {

        Random random = new Random();
        long accountNumber = (long) (random.nextDouble() * 1000000000000L);

        return String.format("%012d", accountNumber);

    }

    private boolean validAccount(Account account) {

        System.out.println("validAccount : " + account);

        if (account.getAddress() == "" ||
                account.getEmail() == "" ||
                account.getContectNumber() == "" ||
                account.getName() == "")
            return false;

        return true;

    }

    public ResponseEntity<String> withdrawMoney(Withdraw withdrawAmount) {
        try {

            if (validacountNumber(withdrawAmount.getAccountNumber())) {

                Account account = accoutnRepository.findByAccountNumber(withdrawAmount.getAccountNumber());
                int availbleBalance = Integer.parseInt(account.getBalance());

                if (availbleBalance >= Integer.parseInt(withdrawAmount.getWithdrawAmount())) {

                    int total = availbleBalance - Integer.parseInt(withdrawAmount.getWithdrawAmount());
                    account.setBalance(String.valueOf(total));
                    accoutnRepository.save(account);
                    return bankUtils.getResponseEntity(MSG.BALANCE + account.getBalance(), HttpStatus.OK);

                } else {
                    return bankUtils.getResponseEntity(MSG.BALANCE_NOT_AVAILABLE, HttpStatus.BAD_REQUEST);
                }

            } else {
                return bankUtils.getResponseEntity(MSG.INVALID_ACCOUNT_NUMBER, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bankUtils.getResponseEntity(MSG.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    private boolean validacountNumber(String accountnumber) {

        Account account = accoutnRepository.findByAccountNumber(accountnumber);

        if (account != null) {
            return true;
        }

        return false;
    }

    public ResponseEntity<String> depositAmount(Diposit depositAmount) {
        try {

            if (validacountNumber(depositAmount.getAccountNumber())) {

                Account account = accoutnRepository.findByAccountNumber(depositAmount.getAccountNumber());
                int availbleBalance = Integer.parseInt(account.getBalance());
                int dispositBalance = Integer.parseInt(depositAmount.getDipositAmount());
                int total = availbleBalance + dispositBalance;
                account.setBalance(String.valueOf(total));
                accoutnRepository.save(account);
                return bankUtils.getResponseEntity(MSG.BALANCE + account.getBalance(), HttpStatus.OK);

            } else {
                return bankUtils.getResponseEntity(MSG.INVALID_ACCOUNT_NUMBER, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bankUtils.getResponseEntity(MSG.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> getBalance(Map<String, String> accountNumber) {

        try {

            if (validacountNumber(accountNumber.get("AC"))) {

                Account account = accoutnRepository.findByAccountNumber(accountNumber.get("AC"));
                return bankUtils.getResponseEntity(MSG.BALANCE + account.getBalance(), HttpStatus.OK);

            }

            return bankUtils.getResponseEntity(MSG.INVALID_ACCOUNT_NUMBER, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bankUtils.getResponseEntity(MSG.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> moneyTransfer(MoneyTransfer moneyTransfer) {
        try {

            if (validacountNumber(moneyTransfer.getFrom()) && validacountNumber(moneyTransfer.getTo())) {
                System.out.println("insaid if");

                Account account = accoutnRepository.findByAccountNumber(moneyTransfer.getFrom());
                int availbleBalance = Integer.parseInt(account.getBalance());

                if (availbleBalance >= Integer.parseInt(moneyTransfer.getAmount())) {

                    Withdraw withdraw = new Withdraw(moneyTransfer.getFrom(), moneyTransfer.getAmount());
                    withdrawMoney(withdraw);
                    System.out.println("after withdraw");
                    Diposit dp = new Diposit(moneyTransfer.getTo(), moneyTransfer.getAmount());
                    depositAmount(dp);
                    System.out.println("after diposit");

                    return bankUtils.getResponseEntity(MSG.TRANSECTION_COMPLETE, HttpStatus.OK);

                }
                return bankUtils.getResponseEntity(MSG.BALANCE_NOT_AVAILABLE, HttpStatus.BAD_REQUEST);
            }
            return bankUtils.getResponseEntity(MSG.INVALID_ACCOUNT_NUMBER, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bankUtils.getResponseEntity(MSG.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
