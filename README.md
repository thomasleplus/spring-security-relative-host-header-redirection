# Spring Security relative Host header redirection

How to configure Spring Security to use a relative redirection during sign-in?

[![Maven](https://github.com/thomasleplus/spring-security-relative-host-header-redirection/workflows/Maven/badge.svg)](https://github.com/thomasleplus/spring-security-relative-host-header-redirection/actions?query=workflow:"Maven")

By default, when an unauthenticated user requests a protected resource, Spring Security's `LoginUrlAuthenticationEntryPoint` issues an absolute redirect to the login page, building the URL from the incoming request's `Host` header. In deployments behind a reverse proxy, load balancer, or any infrastructure that rewrites or spoofs the `Host` header, this can send the user to an unintended (or attacker-controlled) host.

This project seeks how to configure Spring Security to issue a **relative** redirect to the login page instead, so the browser stays on whatever host it originally connected to and the `Host` header is never trusted for redirect target construction.

The unit tests in this project will fail until a solution is found.

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct and the process for submitting pull requests.

## Security

Please read [SECURITY.md](SECURITY.md) for details on our security policy and how to report security vulnerabilities.

## Code of Conduct

Please read [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md) for details on our code of conduct.

## License

This project is licensed under the terms of the [LICENSE](LICENSE) file.
