package com.gem.sistema.business.service.catalogos;

import com.gem.sistema.business.dao.ReciboDAO;
import com.gem.sistema.business.domain.Recibo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "reciboService")
public class ReciboServiceImpl implements ReciboService {
    @Autowired
    private ReciboDAO reciboDAO;

    @Override
    public void save(Recibo recibo) {
          this.reciboDAO.save(recibo);
    }
}
