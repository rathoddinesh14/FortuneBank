Steps to install and configure MariaDB on an Ubuntu system:

```markdown
# MariaDB Installation and Configuration on Ubuntu

This guide provides step-by-step instructions to install and configure MariaDB on an Ubuntu system.

## Step 1: Update the Package Index

Update the package index to ensure you have the latest information about available packages.

```bash
sudo apt update
```

## Step 2: Install MariaDB Server

Install the MariaDB server package.

```bash
sudo apt install mariadb-server
```

## Step 3: Secure MariaDB Installation

Run the security script that comes pre-installed with MariaDB to remove some insecure default settings and improve security.

```bash
sudo mysql_secure_installation
```

You will be prompted to enter the root password. Follow the instructions and answer the prompts:
- Set the root password.
- Remove anonymous users.
- Disallow root login remotely.
- Remove test database and access to it.
- Reload privilege tables.

## Step 4: Start and Enable MariaDB Service

Ensure that MariaDB is running and enabled to start on boot.

```bash
sudo systemctl start mariadb
sudo systemctl enable mariadb
```

You can check the status of the MariaDB service with:

```bash
sudo systemctl status mariadb
```

## Step 5: Test MariaDB Installation

To verify that MariaDB is installed and working correctly, log in to the MariaDB console using the root user.

```bash
sudo mysql -u root -p
```

Enter the root password you set during the secure installation process. Once logged in, you will see the MariaDB prompt.

```sql
Welcome to the MariaDB monitor.  Commands end with ; or \g.
Your MariaDB connection id is 1
Server version: 10.3.31-MariaDB-0ubuntu0.20.04.1 Ubuntu 20.04

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

MariaDB [(none)]>
```

To exit the MariaDB prompt, type:

```sql
exit;
```

## Additional Configuration (Optional)

Depending on your requirements, you might need to configure MariaDB further.

### Configure Remote Access

If you need to allow remote access to MariaDB, you will need to edit the MariaDB configuration file.

1. Open the configuration file:

    ```bash
    sudo nano /etc/mysql/mariadb.conf.d/50-server.cnf
    ```

2. Find the `[mysqld]` section and comment out the `bind-address` directive or set it to `0.0.0.0`:

    ```ini
    #bind-address            = 127.0.0.1
    ```

    or

    ```ini
    bind-address            = 0.0.0.0
    ```

3. Save and close the file.

4. Restart MariaDB to apply the changes:

    ```bash
    sudo systemctl restart mariadb
    ```

5. Ensure you create a user with remote access privileges and update the firewall rules if necessary.

```sql
GRANT ALL PRIVILEGES ON *.* TO 'your_username'@'%' IDENTIFIED BY 'your_password';
FLUSH PRIVILEGES;
```

## Conclusion

You now have MariaDB installed and running on your Ubuntu system. You can start creating databases, users, and managing your data. For more detailed configuration and usage, refer to the [MariaDB documentation](https://mariadb.com/kb/en/documentation/).