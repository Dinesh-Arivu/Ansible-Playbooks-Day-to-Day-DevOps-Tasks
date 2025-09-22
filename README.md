
# 🚀 DevOps Day-to-Day Tasks with Ansible

Automate **provisioning, configuration management, deployments, monitoring, and backups** using Ansible playbooks. This repository provides ready-to-use examples for daily DevOps activities.

![Ansible](https://img.shields.io/badge/Tool-Ansible-blue) ![DevOps](https://img.shields.io/badge/Role-DevOps-green)

---

## 📌 Features

* Server provisioning & package installation
* User & access management
* Application deployment & configuration
* Security patching & monitoring
* Log rotation & cleanup
* Docker & Kubernetes automation
* CI/CD pipeline integration
* Backup & recovery automation

---

## 🛠️ Example Playbooks

<details>
<summary>1. Provision Servers</summary>

```yaml
- name: Provision servers with base packages
  hosts: all
  become: yes
  tasks:
    - name: Update apt cache
      apt:
        update_cache: yes

    - name: Install common packages
      apt:
        name: [git, curl, vim, htop]
        state: present
```

</details>

<details>
<summary>2. User & Access Management</summary>

```yaml
- name: Create DevOps users
  hosts: all
  become: yes
  tasks:
    - name: Create user devops
      user:
        name: devops
        shell: /bin/bash
        state: present

    - name: Add authorized key for devops
      authorized_key:
        user: devops
        key: "{{ lookup('file', '~/.ssh/id_rsa.pub') }}"
```

</details>

<details>
<summary>3. Application Deployment</summary>

```yaml
- name: Deploy application
  hosts: webservers
  become: yes
  tasks:
    - name: Pull latest code
      git:
        repo: 'https://github.com/example/app.git'
        dest: /var/www/app
        version: main

    - name: Restart app service
      systemd:
        name: app
        state: restarted
```

</details>

<details>
<summary>4. Configuration Management</summary>

```yaml
- name: Configure Nginx
  hosts: webservers
  become: yes
  tasks:
    - name: Deploy nginx.conf
      copy:
        src: templates/nginx.conf.j2
        dest: /etc/nginx/nginx.conf
      notify: restart nginx

  handlers:
    - name: restart nginx
      systemd:
        name: nginx
        state: restarted
```

</details>

<details>
<summary>5. Patching & Security Updates</summary>

```yaml
- name: Apply security updates
  hosts: all
  become: yes
  tasks:
    - name: Upgrade all packages
      apt:
        upgrade: dist
        autoremove: yes
```

</details>

<details>
<summary>6. Service Monitoring & Restart</summary>

```yaml
- name: Ensure services are running
  hosts: all
  become: yes
  tasks:
    - name: Ensure Docker is running
      systemd:
        name: docker
        state: started
        enabled: yes
```

</details>

<details>
<summary>7. Log Management</summary>

```yaml
- name: Clear old logs
  hosts: all
  become: yes
  tasks:
    - name: Remove logs older than 7 days
      find:
        paths: /var/log
        age: 7d
        recurse: yes
      register: old_logs

    - name: Delete old logs
      file:
        path: "{{ item.path }}"
        state: absent
      loop: "{{ old_logs.files }}"
```

</details>

<details>
<summary>8. Docker & Kubernetes Automation</summary>

**Docker Example:**

```yaml
- name: Deploy Docker container
  hosts: docker_nodes
  become: yes
  tasks:
    - name: Run Nginx container
      docker_container:
        name: mynginx
        image: nginx:latest
        ports:
          - "80:80"
```

**Kubernetes Example:**

```yaml
- name: Deploy k8s app
  hosts: localhost
  tasks:
    - name: Apply deployment file
      k8s:
        state: present
        src: k8s/deployment.yaml
```

</details>

<details>
<summary>9. CI/CD Integration</summary>

```groovy
ansiblePlaybook(
  playbook: 'deploy.yml',
  inventory: 'inventory.ini'
)
```

</details>

<details>
<summary>10. Backups & Recovery</summary>

```yaml
- name: Backup MySQL database
  hosts: dbservers
  become: yes
  tasks:
    - name: Dump MySQL DB
      command: mysqldump -u root -p{{ mysql_root_password }} mydb > /backup/mydb-{{ ansible_date_time.date }}.sql
```

</details>

---

## ✅ Usage

1. Clone this repo:

```bash
git clone https://github.com/Dinesh-Arivu/Ansible-Playbooks-Day-to-Day-DevOps-Tasks.git
```

2. Update `inventory.ini` with your hosts.

3. Run a playbook:

```bash
ansible-playbook -i inventory.ini playbook.yml
```

---

## 📂 Folder Structure (Recommended)

```
devops-ansible-tasks/
├── inventory.ini
├── playbooks/
│   ├── provisioning.yml
│   ├── deploy_app.yml
│   ├── config.yml
│   └── backup.yml
├── templates/
│   └── nginx.conf.j2
└── README.md
```

