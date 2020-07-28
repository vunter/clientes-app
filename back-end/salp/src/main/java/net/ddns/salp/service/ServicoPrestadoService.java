package net.ddns.salp.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import net.ddns.salp.model.dto.ServicoPrestadoFiltroDTO;
import net.ddns.salp.model.entity.QCliente;
import net.ddns.salp.model.entity.QServicoPrestado;
import net.ddns.salp.model.entity.ServicoPrestado;
import net.ddns.salp.model.repository.ServicoPrestadoRepository;
import net.ddns.salp.util.BigDecimalConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
public class ServicoPrestadoService {

    public static final String PATTERN_DATE = "yyyy-MM-dd";
    private final ServicoPrestadoRepository repository;
    private final EntityManager entityManager;

    @Autowired
    public ServicoPrestadoService(ServicoPrestadoRepository repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    public ServicoPrestado salvar(ServicoPrestado servicoPrestado) {
        return repository.save(servicoPrestado);
    }

    public ServicoPrestado buscaServicoPorID(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço com id " + id + " inexistente!"));
    }

    public List<ServicoPrestado> listar(ServicoPrestadoFiltroDTO servicoPrestadoFiltroDTO) {

        QServicoPrestado servicoPrestado = QServicoPrestado.servicoPrestado;
        QCliente cliente = QCliente.cliente;

        var query = new JPAQueryFactory(entityManager).select(servicoPrestado).from(servicoPrestado);

        query.leftJoin(servicoPrestado.cliente, cliente);

        if (Objects.nonNull(servicoPrestadoFiltroDTO.getCpfCliente()) && !servicoPrestadoFiltroDTO.getCpfCliente().isEmpty())
            query.where(cliente.cpf.like(servicoPrestadoFiltroDTO.getCpfCliente()));

        if (Objects.nonNull(servicoPrestadoFiltroDTO.getNomeCliente()) && !servicoPrestadoFiltroDTO.getNomeCliente().isEmpty())
            query.where(cliente.nome.contains(servicoPrestadoFiltroDTO.getNomeCliente())
                    .or(cliente.lastName.contains(servicoPrestadoFiltroDTO.getNomeCliente())));

        if (Objects.nonNull(servicoPrestadoFiltroDTO.getDtFim()) && !servicoPrestadoFiltroDTO.getDtFim().isEmpty()) {
            query.where(servicoPrestado.data.between(LocalDate.parse(servicoPrestadoFiltroDTO.getDtInicio(), DateTimeFormatter.ofPattern(PATTERN_DATE)),
                    LocalDate.parse(servicoPrestadoFiltroDTO.getDtFim(), DateTimeFormatter.ofPattern(PATTERN_DATE))));
        } else if (Objects.nonNull(servicoPrestadoFiltroDTO.getDtInicio()) && !servicoPrestadoFiltroDTO.getDtInicio().isEmpty()) {
            query.where(servicoPrestado.data.goe(LocalDate.parse(servicoPrestadoFiltroDTO.getDtInicio(), DateTimeFormatter.ofPattern(PATTERN_DATE))));
        }

        if (Objects.nonNull(servicoPrestadoFiltroDTO.getDescricao()) && !servicoPrestadoFiltroDTO.getDescricao().isEmpty())
            query.where(servicoPrestado.descricao.contains(servicoPrestadoFiltroDTO.getDescricao()));

        if (Objects.nonNull(servicoPrestadoFiltroDTO.getValor()) && !servicoPrestadoFiltroDTO.getValor().isEmpty())
            query.where(servicoPrestado.valor.eq(BigDecimalConverter.converter(servicoPrestadoFiltroDTO.getValor())));

        return query.fetch();
    }

}
