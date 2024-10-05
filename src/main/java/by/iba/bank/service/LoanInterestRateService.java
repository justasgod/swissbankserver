package by.iba.bank.service;

import by.iba.bank.model.entity.Loan;
import by.iba.bank.model.entity.LoanInterestRate;
import by.iba.bank.repository.AccountRepository;
import by.iba.bank.repository.LoanInterestRateRepository;
import by.iba.bank.repository.LoanRepository;
import by.iba.bank.repository.RepositoryCreator;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static java.lang.Math.exp;

public class LoanInterestRateService implements Service<LoanInterestRate>{
    @Override
    public LoanInterestRate findEntity(Integer id) {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            LoanInterestRateRepository loanInterestRateRepository = repositoryCreator.getLoanInterestRates();

            return loanInterestRateRepository.findById(id);
        }
    }

    @Override
    public void saveEntity(LoanInterestRate entity) {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            LoanInterestRateRepository loanInterestRateRepository = repositoryCreator.getLoanInterestRates();
            if(loanInterestRateRepository.save(entity))
                System.out.println("OK");
            else
                System.out.println("Error");
        }
    }

    @Override
    public void deleteEntity(LoanInterestRate entity) {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            LoanInterestRateRepository loanInterestRateRepository = repositoryCreator.getLoanInterestRates();
            if(loanInterestRateRepository.delete(entity))
                System.out.println("OK");
            else
                System.out.println("Error");
        }
    }

    @Override
    public void updateEntity(LoanInterestRate entity) {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            LoanInterestRateRepository loanInterestRateRepository = repositoryCreator.getLoanInterestRates();
            if(loanInterestRateRepository.update(entity))
                System.out.println("OK");
            else
                System.out.println("Error");
        }
    }

    @Override
    public List<LoanInterestRate> findAllEntities() {
        return null;
    }

    public LoanInterestRate createByEilerFormula(BigDecimal rate){
        LoanInterestRate loanInterestRate = new LoanInterestRate();

        loanInterestRate.setTypeOfCount("EILER");
        loanInterestRate.setEffectiveInterestRate(BigDecimal.valueOf(Math.exp(rate.doubleValue()) - 1).setScale(4, RoundingMode.HALF_UP));

        return loanInterestRate;
    }

    public LoanInterestRate createByErdashFormula(BigDecimal rate, int r){
        LoanInterestRate loanInterestRate = new LoanInterestRate();

        loanInterestRate.setTypeOfCount("ERDASH");
        loanInterestRate.setEffectiveInterestRate((rate.divide(BigDecimal.valueOf(r), RoundingMode.HALF_UP).add(BigDecimal.valueOf(1))).pow(r).subtract(BigDecimal.valueOf(1)));

        return loanInterestRate;
    }

    public LoanInterestRate createByLiborFormula(@NotNull BigDecimal amount, BigDecimal finalAmount){
        LoanInterestRate loanInterestRate = new LoanInterestRate();

        loanInterestRate.setTypeOfCount("LIBOR");
        loanInterestRate.setEffectiveInterestRate(finalAmount.divide(amount, RoundingMode.HALF_UP).subtract(BigDecimal.valueOf(1)));

        return loanInterestRate;
    }

  /*  public LoanInterestRate createByMacCLuraFormula(@NotNull BigDecimal amount, BigDecimal finalAmount, Integer term){
        LoanInterestRate loanInterestRate = new LoanInterestRate();

        loanInterestRate.setTypeOfCount("MACCLURA");

        BigDecimal rate = BigDecimal.valueOf((Math.pow(finalAmount.divide(amount, RoundingMode.HALF_UP).doubleValue(), 1/(double)term)  - 1));

        loanInterestRate.setEffectiveInterestRate(rate.setScale(4, RoundingMode.HALF_UP));

        return loanInterestRate;
    }*/

}
