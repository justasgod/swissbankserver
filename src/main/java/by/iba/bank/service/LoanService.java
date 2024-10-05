package by.iba.bank.service;

import by.iba.bank.model.entity.Client;
import by.iba.bank.model.entity.Loan;
import by.iba.bank.model.entity.LoanInterestRate;
import by.iba.bank.repository.ClientRepository;
import by.iba.bank.repository.LoanInterestRateRepository;
import by.iba.bank.repository.LoanRepository;
import by.iba.bank.repository.RepositoryCreator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class LoanService implements Service<Loan>{
    @Override
    public Loan findEntity(Integer id) {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            LoanRepository loanRepository = repositoryCreator.getLoanRepository();

            return loanRepository.findById(id);
        }
    }

    @Override
    public void saveEntity(Loan entity) {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            LoanRepository loanRepository = repositoryCreator.getLoanRepository();
            if(loanRepository.save(entity))
                System.out.println("OK");
            else
                System.out.println("Error");
        }
    }

    @Override
    public void deleteEntity(Loan entity) {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            LoanRepository loanRepository = repositoryCreator.getLoanRepository();
            if(loanRepository.delete(entity))
                System.out.println("OK");
            else
                System.out.println("Error");
        }
    }

    @Override
    public void updateEntity(Loan entity) {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            LoanRepository loanRepository = repositoryCreator.getLoanRepository();
            if(loanRepository.update(entity))
                System.out.println("OK");
            else
                System.out.println("Error");
        }
    }

    @Override
    public List<Loan> findAllEntities() {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            LoanRepository loanRepository  = repositoryCreator.getLoanRepository();
            return loanRepository.findAll(Loan.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
    public List<Loan> findAllEntitiesByClient(Client client) {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            LoanRepository loanRepository  = repositoryCreator.getLoanRepository();
            return loanRepository.findByClient(client);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public List<LoanInterestRate> processApplication(Loan requestLoan){
        BigDecimal amount = requestLoan.getAmount();
        BigDecimal term = BigDecimal.valueOf(requestLoan.getTerm()).multiply(BigDecimal.valueOf(12));

        requestLoan.setInterestRate(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0.05, 0.25)).setScale(4, RoundingMode.HALF_UP));
        BigDecimal finalAmount = amount.multiply(requestLoan.getInterestRate().add(BigDecimal.valueOf(1)).add(BigDecimal.valueOf(0.01)));


        requestLoan.setStatus("WAIT");
        requestLoan.setMonthlyPayment(finalAmount.divide(term, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP));

        requestLoan.getAccount().setOpenDate(requestLoan.getIssueDate());

        List<LoanInterestRate> rates = new ArrayList<>();

        LoanInterestRateService loanInterestRateService = new LoanInterestRateService();
        rates.add( loanInterestRateService.createByEilerFormula(requestLoan.getInterestRate()));
        rates.add(loanInterestRateService.createByErdashFormula(requestLoan.getInterestRate(), 1));
       /* rates.add(loanInterestRateService.createByMacCLuraFormula(amount, finalAmount, requestLoan.getTerm()));*/
        rates.add(loanInterestRateService.createByLiborFormula(amount, finalAmount));

        rates.get(0).setLoan(requestLoan);


        return rates;
    }
}
