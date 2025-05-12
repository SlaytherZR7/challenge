package com.ntt.challenge.service;

import com.ntt.challenge.dto.MovimientoRequestDTO;
import com.ntt.challenge.dto.MovimientoResponseDTO;
import com.ntt.challenge.exception.MovimientoNoEncontradoException;
import com.ntt.challenge.model.Cliente;
import com.ntt.challenge.model.Cuenta;
import com.ntt.challenge.model.Movimiento;
import com.ntt.challenge.model.TipoCuenta;
import com.ntt.challenge.repository.CuentaRepository;
import com.ntt.challenge.repository.MovimientoRepository;
import com.ntt.challenge.utils.MovimientoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovimientoServiceImplTest {

    @Mock
    private MovimientoRepository movimientoRepository;

    @Mock
    private CuentaRepository cuentaRepository;

    @Mock
    private MovimientoMapper movimientoMapper;

    @InjectMocks
    private MovimientoServiceImpl movimientoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crear() {
        UUID cuentaId = UUID.randomUUID();
        MovimientoRequestDTO requestDTO = new MovimientoRequestDTO(cuentaId, BigDecimal.valueOf(100), null);

        Cliente cliente = new Cliente();
        cliente.setNombre("Juan Pérez");

        Cuenta cuenta = new Cuenta();
        cuenta.setId(cuentaId);
        cuenta.setSaldoInicial(BigDecimal.valueOf(200));
        cuenta.setCliente(cliente);
        cuenta.setNumeroCuenta("123456");
        cuenta.setTipoCuenta(TipoCuenta.AHORROS);
        cuenta.setEstado(true);

        Movimiento movimiento = new Movimiento();

        when(cuentaRepository.findById(cuentaId)).thenReturn(Optional.of(cuenta));
        when(movimientoMapper.toEntity(requestDTO)).thenReturn(movimiento);
        when(movimientoRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        MovimientoResponseDTO result = movimientoService.crear(requestDTO);

        assertEquals("Juan Pérez", result.getCliente());
        assertEquals(BigDecimal.valueOf(200), result.getSaldoInicial());
        assertEquals(BigDecimal.valueOf(300), result.getSaldoDisponible());
        assertEquals(BigDecimal.valueOf(100), result.getMovimiento());
    }

    @Test
    void obtener() {
        UUID movimientoId = UUID.randomUUID();
        Movimiento movimiento = new Movimiento();
        MovimientoResponseDTO dto = new MovimientoResponseDTO();

        when(movimientoRepository.findById(movimientoId)).thenReturn(Optional.of(movimiento));
        when(movimientoMapper.toDTO(movimiento)).thenReturn(dto);

        MovimientoResponseDTO result = movimientoService.obtener(movimientoId);

        assertEquals(dto, result);
    }

    @Test
    void listar() {
        List<Movimiento> movimientos = List.of(new Movimiento(), new Movimiento());
        List<MovimientoResponseDTO> dtoList = List.of(new MovimientoResponseDTO(), new MovimientoResponseDTO());

        when(movimientoRepository.findAll()).thenReturn(movimientos);
        when(movimientoMapper.toDTOList(movimientos)).thenReturn(dtoList);

        List<MovimientoResponseDTO> result = movimientoService.listar();

        assertEquals(2, result.size());
    }

    @Test
    void actualizar() {
        assertThrows(UnsupportedOperationException.class, () ->
                movimientoService.actualizar(UUID.randomUUID(), null));
    }

    @Test
    void eliminar() {
        UUID id = UUID.randomUUID();
        Movimiento movimiento = new Movimiento();
        when(movimientoRepository.findById(id)).thenReturn(Optional.of(movimiento));

        movimientoService.eliminar(id);

        verify(movimientoRepository, times(1)).delete(movimiento);
    }

    @Test
    void eliminar_notFound() {
        UUID id = UUID.randomUUID();
        when(movimientoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(MovimientoNoEncontradoException.class, () -> movimientoService.eliminar(id));
    }
}
