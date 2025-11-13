# @author Tarush Gupta
# @date 2025-11-12

	.data
	varb: .word 0
	varx: .word 0
	vary: .word 0
	vari: .word 0
	varsum: .word 0
	
	.text
	.globl main
main:
	li $v0, 2
	la $t0 varx
	sw $v0 ($t0)
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	li $v0, 3
	subu $sp $sp 4
	sw $v0 ($sp)
	la $t0 varx
	lw $v0 ($t0)
	lw $t0 ($sp)
	addu $sp $sp 4
	mul $v0, $v0, $t0
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0, $t0, $v0
	la $t0 vary
	sw $v0 ($t0)
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	la $t0 vary
	lw $v0 ($t0)
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0, $t0, $v0
	la $t0 varx
	sw $v0 ($t0)
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	la $t0 vary
	lw $v0 ($t0)
	lw $t0 ($sp)
	addu $sp $sp 4
	mul $v0, $v0, $t0
	move $a0, $v0
	li $v0, 1
	syscall
	li $a0, 10
	li $v0, 11
	syscall
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	la $t0 vary
	lw $v0 ($t0)
	lw $t0 ($sp)
	addu $sp $sp 4
	ble $t0 $v0 endif1
	la $t0 varx
	lw $v0 ($t0)
	move $a0, $v0
	li $v0, 1
	syscall
	li $a0, 10
	li $v0, 11
	syscall
	la $t0 vary
	lw $v0 ($t0)
	move $a0, $v0
	li $v0, 1
	syscall
	li $a0, 10
	li $v0, 11
	syscall
endif1:
	li $v0, 0
	la $t0 varx
	sw $v0 ($t0)
startwhile2:
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	li $v0, 8
	lw $t0 ($sp)
	addu $sp $sp 4
	bge $t0 $v0 endwhile2
	la $t0 varx
	lw $v0 ($t0)
	move $a0, $v0
	li $v0, 1
	syscall
	li $a0, 10
	li $v0, 11
	syscall
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	li $v0, 2
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0, $t0, $v0
	la $t0 varx
	sw $v0 ($t0)
	j startwhile2
endwhile2:
	li $v0, 0
	la $t0 varsum
	sw $v0 ($t0)
	li $v0, 1
	la $t0 vari
	sw $v0 ($t0)
startfor3:
	la $t0 vari
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	li $v0, 5
	lw $t0 ($sp) #pop loop variable
	addu $sp $sp 4
	bgt $t0 $v0 endfor3
	la $t0 varsum
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	la $t0 vari
	lw $v0 ($t0)
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0, $t0, $v0
	la $t0 varsum
	sw $v0 ($t0)
	la $t0 varsum
	lw $v0 ($t0)
	move $a0, $v0
	li $v0, 1
	syscall
	li $a0, 10
	li $v0, 11
	syscall
	la $t0 vari
	lw $v0 ($t0)
	addi $v0 $v0 1
	la $t0 vari
	sw $v0 ($t0)
	j startfor3
endfor3:
	li $v0, 5
	syscall
	la $t0 varb
	sw $v0 ($t0)
	la $t0 varb
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	li $v0, 10
	lw $t0 ($sp)
	addu $sp $sp 4
	bge $t0 $v0 endif4
	la $t0 varb
	lw $v0 ($t0)
	move $a0, $v0
	li $v0, 1
	syscall
	li $a0, 10
	li $v0, 11
	syscall
endif4:
	li $v0, 10
	syscall
