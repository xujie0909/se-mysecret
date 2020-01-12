package com.xujie.mysecret.dao;

import com.xujie.mysecret.entity.Trace;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class TraceDao implements JpaRepository<Trace, Long> {
    @Override
    public List<Trace> findAll() {
        return null;
    }

    @Override
    public List<Trace> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Trace> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Trace> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Trace trace) {

    }

    @Override
    public void deleteAll(Iterable<? extends Trace> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Trace> S save(S s) {
        return null;
    }

    @Override
    public <S extends Trace> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Trace> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Trace> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Trace> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Trace getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Trace> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Trace> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Trace> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Trace> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Trace> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Trace> boolean exists(Example<S> example) {
        return false;
    }
}
