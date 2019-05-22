package com.choonsky.orderman.repository;

import com.choonsky.orderman.model.Attachment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer>
{
}