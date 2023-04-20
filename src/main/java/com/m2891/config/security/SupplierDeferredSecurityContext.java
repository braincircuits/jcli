package com.m2891.config.security;

import org.springframework.security.core.context.DeferredSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolderStrategy;

import java.util.function.Supplier;

final class SupplierDeferredSecurityContext implements DeferredSecurityContext
{
	private final Supplier<SecurityContext> supplier;

	private final SecurityContextHolderStrategy strategy;

	private SecurityContext securityContext;

	private boolean missingContext;

	SupplierDeferredSecurityContext(Supplier<SecurityContext> supplier, SecurityContextHolderStrategy strategy) {
		this.supplier = supplier;
		this.strategy = strategy;
	}

	@Override
	public SecurityContext get() {
		init();
		return this.securityContext;
	}

	@Override
	public boolean isGenerated() {
		init();
		return this.missingContext;
	}

	private void init() {
		if (this.securityContext != null) {
			return;
		}

		this.securityContext = this.supplier.get();
		this.missingContext = (this.securityContext == null);
		if (this.missingContext) {
			this.securityContext = this.strategy.createEmptyContext();
//			if (logger.isTraceEnabled()) {
//				logger.trace(LogMessage.format("Created %s", this.securityContext));
//			}
		}
	}

}
