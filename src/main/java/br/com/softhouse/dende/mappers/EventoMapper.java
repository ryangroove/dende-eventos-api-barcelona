package br.com.softhouse.dende.mappers;

import br.com.softhouse.dende.dto.EventoRequestDTO;
import br.com.softhouse.dende.dto.EventoResponseDTO;
import br.com.softhouse.dende.model.Evento;
import br.com.softhouse.dende.model.EventoBuilder;

public class EventoMapper {

    public static Evento toModel(EventoRequestDTO dto, Long organizadorId) {

        return new EventoBuilder()
                .organizadorId(organizadorId)
                .paginaEvento(dto.getPaginaEvento())
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .inicio(dto.getInicio())
                .fim(dto.getFim())
                .tipo(dto.getTipo())
                .modalidade(dto.getModalidade())
                .local(dto.getLocal())
                .capacidadeMaxima(dto.getCapacidadeMaxima())
                .precoIngresso(dto.getPrecoIngresso())
                .estorna(dto.isEstorna())
                .taxaEstorno(dto.getTaxaEstorno())
                .build();
    }

    public static EventoResponseDTO toDTO(Evento evento) {

        EventoResponseDTO dto = new EventoResponseDTO();

        dto.setId(evento.getId());
        dto.setNome(evento.getNome());
        dto.setDescricao(evento.getDescricao());
        dto.setInicio(evento.getInicio());
        dto.setFim(evento.getFim());
        dto.setTipo(evento.getTipo());
        dto.setModalidade(evento.getModalidade());
        dto.setLocal(evento.getLocal());
        dto.setPrecoIngresso(evento.getPrecoIngresso());
        dto.setCapacidadeMaxima(evento.getCapacidadeMaxima());
        dto.setAtivo(evento.isAtivo());

        return dto;
    }
}