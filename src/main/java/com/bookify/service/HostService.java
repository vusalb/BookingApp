package com.bookify.service;

import com.bookify.dto.request.HostRequest;
import com.bookify.dto.response.HostResponse;

public interface HostService {

	public String createHost(HostRequest request);

	public HostResponse getHostById(Long id);

	public String updateHostById(Long id, HostRequest request);

	public String deleteHostById(Long id);

	public HostResponse findHostByEmail(String email);

}
