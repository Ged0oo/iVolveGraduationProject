output "jenkins_public_ip" {
  value = module.server.jenkins_instance_public_ip
  description = "Public IP address of the Jenkins EC2 instance"
}

output "jenkins_instance_id" {
  value = module.server.jenkins_instance_id
  description = "ID of the Jenkins EC2 instance"
}
