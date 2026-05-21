package repository;
import models.Agreement;
import java.util.ArrayList;
import java.util.List;
public class AgreementRepository {
    private List<Agreement> agreements = new ArrayList<>();
    public void addAgreement(Agreement a) {
        agreements.add(a);
    }
    public List<Agreement> getAllAgreements() {
        return agreements;
    }
}
